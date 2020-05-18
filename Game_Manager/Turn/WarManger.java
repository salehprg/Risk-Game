package Game_Manager.Turn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import Map.Country;

public class WarManger {

    public int DoWar(TurnManager turnManager)
    {

        Country AttackerCountry = turnManager.FirstCountrySelected;
        Country DefenderCountry = turnManager.SecondCountrySelected;

        // Open Dialog For Attacker Soldier
        // Open Dialog For Defender Soldier

        int AttackerSoldier = 0;
        int DefenderSoldier = 0;

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
                    return AttackerSoldier;
                }
            }
        }

        

        return -1;
    }
}