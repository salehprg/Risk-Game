package Game_Manager.Turn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import Map.Country;

public class WarManger {

    public static boolean CheckConnection()
    {
        if(TurnManager.getFirstCountrySelected().GetOwnerId() != TurnManager.CurrentPlayer.getPlayerID())
        {
            return false;
        }
        else
        {
            int[] AttackerNeighbour = TurnManager.getFirstCountrySelected().GetNeighborsID();

            for(int i = 0; i < AttackerNeighbour.length; i++)
            {
                if(AttackerNeighbour[i] == TurnManager.getSecondCountrySelected().GetCountryID())
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean CheckWar(int AttackerSoldier)
    {
        if(AttackerSoldier > TurnManager.getFirstCountrySelected().GetSoldierCount() - 1 || AttackerSoldier <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public static OutputResult DoWar(int AttackerSoldier , int DefenderSoldier)
    {
        OutputResult result = new OutputResult();

        Country AttackerCountry = TurnManager.FirstCountrySelected;
        Country DefenderCountry = TurnManager.SecondCountrySelected;


        int AttackerDiceCount = (AttackerSoldier > 3 ? 3 : AttackerSoldier);
        int DefenderDiceCount = (DefenderSoldier > 2 ? 2 : DefenderSoldier);

        ArrayList<Integer> AttackerDiceNumbers = new ArrayList<Integer>();
        ArrayList<Integer> DefenderDiceNumbers = new ArrayList<Integer>();

        Random rnd = new Random();

        for(int i = 0;i < AttackerDiceCount; i++)
        {
            int RndDiceNumber = rnd.nextInt(6) + 1;
            AttackerDiceNumbers.add(RndDiceNumber);
        }

        for(int i = 0;i < DefenderDiceCount; i++)
        {
            int RndDiceNumber = rnd.nextInt(6) + 1;
            DefenderDiceNumbers.add(RndDiceNumber);
        }

        //Sort Dice Numbers
        Collections.sort(AttackerDiceNumbers);
        Collections.reverse(AttackerDiceNumbers);
        Collections.sort(DefenderDiceNumbers);
        Collections.reverse(DefenderDiceNumbers);

        result.AttackerDiceNumbers = AttackerDiceNumbers;
        result.DefenderDiceNumbers = DefenderDiceNumbers;

        for(int i = 0; i < (AttackerDiceNumbers.size() > DefenderDiceNumbers.size() ? DefenderDiceNumbers.size() : AttackerDiceNumbers.size()) ; i++)
        {
            if(DefenderDiceNumbers.get(i) >= AttackerDiceNumbers.get(i))
            {
                AttackerSoldier--;
                AttackerCountry.RemoveSoldier(1);
            }
            else
            {
                DefenderSoldier--;
                DefenderCountry.RemoveSoldier(1);
                
                if(DefenderCountry.GetSoldierCount() <= 0)
                {
                    result.AttckSoldier = AttackerSoldier;
                    result.AttckWin = true;
                    return result;
                }
            }
        }

        
        result.AttckWin = false;
        return result;
    }
}