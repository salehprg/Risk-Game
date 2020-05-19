package Game_Manager;

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

    public SoldierManager soldierManager;
    public WarManger warManger;
    public TurnManager turnManager;
    public Move move;

    GameData gameData;
    UIManager uiManager;

    public enum State
    {
        DeploySoldier,
        Move,
        War
    }
    State CurrentState = State.DeploySoldier;

    
    public GameManager(List<Player> players)
    {
        soldierManager = new SoldierManager();
        warManger = new WarManger();
        turnManager = new TurnManager(players  , this);
        move = new Move();
        gameData = new GameData(turnManager);

        uiManager = new UIManager(this);
    }

    public void InitializeGame()
    {
        int NumbersPlayers = 2;

        Map.Initialize();
        turnManager.NextTurn();
    }


//#region UI Functions


    public void ChangeState(State _State)
    {
        if(turnManager.getCurrentPlayer().getUnimployedSoldiersCount() == 0)
        {
            CurrentState = _State;
        }
    }


    public void CountryUIClick(Country _Country , boolean PassData , InputModel dataModel)
    {
        switch (CurrentState) {
            case DeploySoldier:
                
                if(!PassData)
                {
                    if(turnManager.getFirstCountrySelected() == null)
                    {
                        turnManager.SetCountrySelected(_Country);
                    }

                    InputModel dialogModel = new InputModel();

                    dialogModel.DeploySoldier = turnManager.getCurrentPlayer().getUnimployedSoldiersCount();

                    uiManager.OpenSoldierInput_Dialog(dialogModel);
                }
                else
                {
                    if(soldierManager.DeploySoldier(dataModel.DeploySoldier , turnManager))
                    {
                        if(turnManager.getCurrentPlayer().getUnimployedSoldiersCount() <= 0)
                        {
                            CurrentState = State.Move;
                        }

                        gameData.UpdateMapInfo(turnManager.getFirstCountrySelected());
                        turnManager.ClearSelectedCountry();
                    }
                    else
                    {
                        InputModel dialogModel = new InputModel();
                        dialogModel.DeploySoldier = turnManager.getCurrentPlayer().getUnimployedSoldiersCount();
                        uiManager.OpenSoldierInput_Dialog(dialogModel);
                    }
                    
                }

                break;

            case Move:

                //Open Dialog For Input Soldier To Move

                int SoldierToMove = 0;

                if(turnManager.getFirstCountrySelected() == null || turnManager.getSecondCountrySelected() == null)
                {
                    turnManager.SetCountrySelected(_Country);
                }

                if(turnManager.getSecondCountrySelected() != null)
                {
                    if(turnManager.CheckMove())
                    {
                        soldierManager.MoveSoldier(SoldierToMove , turnManager);
                        turnManager.ClearSelectedCountry();
                    }
                }

                break;
                
            case War:

                if(turnManager.getFirstCountrySelected() == null || turnManager.getSecondCountrySelected() == null)
                {
                    turnManager.SetCountrySelected(_Country);
                }

                if(turnManager.getSecondCountrySelected() != null)
                {
                    if(turnManager.CheckWar())
                    {
                        int AttackerSoldier = warManger.DoWar(turnManager);
                        if(AttackerSoldier > 0)  // It Means Attacker Win
                        {
                            soldierManager.MoveSoldier(AttackerSoldier, turnManager);
                            turnManager.AddDefenderCountryToAttackerCountry();
                        }
                    }
                }

                turnManager.NextTurn();

                break;
        }
    }


//#endregion


    
}