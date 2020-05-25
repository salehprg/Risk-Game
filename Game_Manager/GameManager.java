package Game_Manager;

import java.util.List;

import Game_Manager.Game_Data.GameData;
import Game_Manager.Turn.*;
import Map.Country;
import Map.Map;
import PlayerManager.Player;
import UI.InputModel;
import UI.UIManager;

public class GameManager {

    //public static SoldierManager SoldierManager;
    //public static WarManger warManger;
    public static TurnManager turnManager;

    GameData gameData;
    UIManager uiManager;

    public enum State
    {
        DeploySoldier,
        Move,
        War
    }

    public GameManager (UIManager _uiManager)
    {
        uiManager = _uiManager;
    }

    public static State CurrentState = State.DeploySoldier;

    public void InitializeGame(int PlayerNumbers , List<Player> _players)
    {
        List<Player> players = _players;

        turnManager = new TurnManager(players  , this);

        Map.Initialize();
        SoldierManager.Initialize(PlayerNumbers);
        TurnManager.HasFirstRunSodlier = true;
        uiManager.InitializeGame();

        turnManager.NextTurn();


        GameData.UpdateGameInfo();
        GameData.UpdateMapInfo();
    }


//#region UI Functions

    public void FinishTurn()
    {
        if(CurrentState != State.DeploySoldier)
        {
            turnManager.NextTurn();
        }
    }

    public static void ChangeState(State _State)
    {
        CurrentState = _State;
        GameData.UpdateGameInfo();
    }

    //passData == true ==> ersal data //passData == false ==> roye keshvar click shode
    public void CountryUIClick(Country _Country , boolean PassData , InputModel dataModel)
    {
        try
        {
            if(PassData && dataModel.IsExited)
            {
                TurnManager.ClearSelectedCountry();
                return;
            }
            else if(!PassData)
            {
                if(TurnManager.getFirstCountrySelected() != null) //It means FirstCountry selected before
                {
                    if(_Country.GetOwnerId() == TurnManager.getCurrentPlayer().getPlayerID() && _Country.GetOwnerId() != -1)
                    {
                        ChangeState(State.Move);
                    }
                    else if(_Country.GetOwnerId() != TurnManager.getCurrentPlayer().getPlayerID() && _Country.GetOwnerId() != -1)
                    {
                        if(CurrentState != State.Move)
                            ChangeState(State.War);
                    }
                }
            }

            switch (CurrentState) {
                case DeploySoldier:
                    boolean HasFirstRunSodlier = false;
                    for(int i = 0;i < TurnManager.getPlayerList().size();i++)
                    {
                        if(TurnManager.getPlayerList().get(i).getFirstRunUnimployedSoldiersCount() > 0)
                        {
                            HasFirstRunSodlier = true;
                        }
                    }
                    
                    if(!PassData)
                    {
                        if(TurnManager.getFirstCountrySelected() == null)
                        {
                            TurnManager.SetCountrySelected(_Country);
                        }
                    }
                    if(!HasFirstRunSodlier)
                    {
                        if(!PassData)
                        {

                            if(TurnManager.CheckDeploy(_Country))
                            {
                                InputModel dialogModel = new InputModel();
                                dialogModel.DeploySoldier = TurnManager.getCurrentPlayer().getUnimployedSoldiersCount();
                                uiManager.OpenSoldierInput_Dialog(dialogModel);
                            }
                            else
                            {
                                System.out.println("Not Yours");
                            }
                        }
                        else
                        {
                            if(SoldierManager.DeploySoldier(dataModel.DeploySoldier , false))
                            {
                                if(TurnManager.getCurrentPlayer().getUnimployedSoldiersCount() <= 0)
                                {
                                    CurrentState = State.War;
                                    //turnManager.NextTurn();
                                }
                                
                                TurnManager.ClearSelectedCountry();
                            }
                            else
                            {
                                InputModel dialogModel = new InputModel();
                                dialogModel.DeploySoldier = TurnManager.getCurrentPlayer().getUnimployedSoldiersCount();
                                uiManager.OpenSoldierInput_Dialog(dialogModel);
                            }
                            
                        }
                    }
                    else
                    {
                        if(SoldierManager.DeploySoldier(1 , true))
                        {

                            turnManager.NextTurn();
                            TurnManager.ClearSelectedCountry();
                        }
                    }

                    break;

                case Move:

                    if(!PassData)
                    {
                        if(TurnManager.getFirstCountrySelected() == null || TurnManager.getSecondCountrySelected() == null)
                        {
                            TurnManager.SetCountrySelected(_Country);
                        }   

                        if(TurnManager.getSecondCountrySelected() != null)
                        {
                            if(MoveManager.CheckMove())
                            {
                                InputModel dialogModel = new InputModel();
                                dialogModel.FirstCountry = TurnManager.getFirstCountrySelected();
                                dialogModel.SecondCountry = TurnManager.getSecondCountrySelected();
                                dialogModel.MoveSoldier = TurnManager.getFirstCountrySelected().GetSoldierCount() - 1;

                                uiManager.OpenSoldierMoveInput_Dialog(dialogModel);
                            }
                            else
                            {
                                TurnManager.ClearSelectedCountry();
                                System.out.println("Cant Move");
                            }
                        }
                    }
                    else
                    {
                        int SoldierToMove = dataModel.MoveSoldier;

                        if(SoldierManager.MoveSoldier(SoldierToMove))
                        {
                            //GameData.UpdateMapInfo();
                            //GameData.UpdateGameInfo();
                            TurnManager.ClearSelectedCountry();
                            MoveManager.CleanCheck();
                        }
                        else
                        {
                            InputModel dialogModel = new InputModel();
                            dialogModel.FirstCountry = TurnManager.getFirstCountrySelected();
                            dialogModel.SecondCountry = TurnManager.getSecondCountrySelected();
                            dialogModel.MoveSoldier = TurnManager.getFirstCountrySelected().GetSoldierCount() - 1;

                            uiManager.OpenSoldierMoveInput_Dialog(dialogModel);
                        }

                    }

                    break;
                    
                case War:

                    if(!PassData)
                    {
                        if(TurnManager.getFirstCountrySelected() == null || TurnManager.getSecondCountrySelected() == null)
                        {
                            TurnManager.SetCountrySelected(_Country);
                        }
                        
                        if(TurnManager.getSecondCountrySelected() != null)
                        {
                            if(WarManger.CheckConnection())
                            {
                                InputModel dialogModel = new InputModel();
                                dialogModel.FirstCountry = TurnManager.getFirstCountrySelected();
                                dialogModel.SecondCountry = TurnManager.getSecondCountrySelected();
                                dialogModel.AttackerSoldier = TurnManager.getFirstCountrySelected().GetSoldierCount() - 1;

                                uiManager.OpenWar_Dialog(dialogModel);
                            }
                        }
                    }
                    else
                    {
                        if(WarManger.CheckWar(dataModel.AttackerSoldier))
                        {
                            OutputResult res = WarManger.DoWar(dataModel.AttackerSoldier , TurnManager.getSecondCountrySelected().GetSoldierCount());
                            if(res != null)
                            {
                                InputModel dialogModel = new InputModel();
                                dialogModel.FirstCountry = TurnManager.getFirstCountrySelected();
                                dialogModel.SecondCountry = TurnManager.getSecondCountrySelected();
                                dialogModel.AttackerSoldier = TurnManager.getFirstCountrySelected().GetSoldierCount() - 1;
                                dialogModel.DfndDiceNumber = res.DefenderDiceNumbers;
                                dialogModel.AttckDiceNumber = res.AttackerDiceNumbers;

                                uiManager.OpenWarResult_Dialog(dialogModel);

                                if(res.AttckSoldier > 0)  // It Means Attacker Win
                                {
                                    TurnManager.AddDefenderCountryToAttackerCountry();
                                    SoldierManager.MoveSoldier(res.AttckSoldier);
                                }
                            }

                            // GameData.UpdateMapInfo();
                            // GameData.UpdateGameInfo();
                            TurnManager.ClearSelectedCountry();
                        }
                        else
                        {
                            InputModel dialogModel = new InputModel();
                            dialogModel.FirstCountry = TurnManager.getFirstCountrySelected();
                            dialogModel.SecondCountry = TurnManager.getSecondCountrySelected();
                            dialogModel.AttackerSoldier = TurnManager.getFirstCountrySelected().GetSoldierCount() - 1;

                            uiManager.OpenWar_Dialog(dialogModel);
                        }
                    }

                    //turnManager.NextTurn();

                    break;
            }
            
            GameData.UpdateMapInfo();
            GameData.UpdateGameInfo();
            
        }
        catch(Exception ex){
            TurnManager.ClearSelectedCountry();
            System.out.println(ex.getMessage());
        }


    }


//#endregion


    
}