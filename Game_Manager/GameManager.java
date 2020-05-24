package Game_Manager;

import java.io.IOException;
import java.util.List;

import Game_Manager.Game_Data.GameData;
import Game_Manager.Turn.*;
import Map.Country;
import Map.Map;
import PlayerManager.Player;
import UI.InputModel;
import UI.UIManager;
import UI.UIUtilities;

public class GameManager {

    //public static SoldierManager SoldierManager;
    //public static WarManger warManger;
    public static TurnManager turnManager;
    public static Move move;

    GameData gameData;
    UIManager uiManager;

    public enum State
    {
        DeploySoldier,
        Move,
        War
    }

    public static State CurrentState = State.DeploySoldier;

    
    public GameManager(List<Player> players)
    {
        // SoldierManager = new SoldierManager();
        // warManger = new WarManger();
        turnManager = new TurnManager(players  , this);
        move = new Move();
        //gameData = new GameData(TurnManager);

        uiManager = new UIManager(this);
    }

    public void InitializeGame()
    {
        int NumbersPlayers = 2;

        Map.Initialize();
        turnManager.NextTurn();

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
                    if(_Country.GetOwnerId() != TurnManager.getCurrentPlayer().getPlayerID() && _Country.GetOwnerId() != -1)
                    {
                        ChangeState(State.War);
                    }
                }
            }

            switch (CurrentState) {
                case DeploySoldier:
                    
                    if(!PassData)
                    {
                        if(TurnManager.getFirstCountrySelected() == null)
                        {
                            TurnManager.SetCountrySelected(_Country);
                        }

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
                        if(SoldierManager.DeploySoldier(dataModel.DeploySoldier))
                        {
                            if(TurnManager.getCurrentPlayer().getUnimployedSoldiersCount() <= 0)
                            {
                                CurrentState = State.Move;
                                //turnManager.NextTurn();
                            }

                            GameData.UpdateGameInfo();
                            GameData.UpdateMapInfo();
                            TurnManager.ClearSelectedCountry();
                        }
                        else
                        {
                            InputModel dialogModel = new InputModel();
                            dialogModel.DeploySoldier = TurnManager.getCurrentPlayer().getUnimployedSoldiersCount();
                            uiManager.OpenSoldierInput_Dialog(dialogModel);
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
                            if(TurnManager.CheckMove())
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
                            GameData.UpdateMapInfo();
                            TurnManager.ClearSelectedCountry();
                            TurnManager.CleanCheck();
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
                            if(TurnManager.CheckWar())
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
                        
                        int AttackerSoldier = WarManger.DoWar(dataModel.AttackerSoldier , TurnManager.getSecondCountrySelected().GetSoldierCount());
                        if(AttackerSoldier > 0)  // It Means Attacker Win
                        {
                            TurnManager.AddDefenderCountryToAttackerCountry();
                            SoldierManager.MoveSoldier(AttackerSoldier);
                        }
                        
                        GameData.UpdateMapInfo();
                        TurnManager.ClearSelectedCountry();
                    }

                    //turnManager.NextTurn();

                    break;
            }
        }
        catch(Exception ex){
            TurnManager.ClearSelectedCountry();
            System.out.println(ex.getMessage());
        }


    }


//#endregion


    
}