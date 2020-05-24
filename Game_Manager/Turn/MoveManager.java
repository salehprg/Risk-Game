package Game_Manager.Turn;

import Map.*;

public class MoveManager {
    public static boolean CheckConnection(Country _FromCountry , Country _ToCountry)
    {
        _FromCountry.SetChecked(true);
        int[] Neighbors = _FromCountry.GetNeighborsID();
        boolean res = false;

        for(int i = 0;i < Neighbors.length ;i++)
        {
            if(!res)
            {
                Country CurrentCountry = Map.getCountry(Neighbors[i]);

                if(Neighbors[i] == _ToCountry.GetCountryID() && CurrentCountry.GetOwnerId() == TurnManager.CurrentPlayer.getPlayerID())
                {
                    System.out.println("True");
                    res = true;
                }
                else if(!CurrentCountry.GetChecked() && CurrentCountry.GetOwnerId() == TurnManager.CurrentPlayer.getPlayerID())
                {
                    System.out.println("Check");
                    if(CheckConnection(CurrentCountry, _ToCountry))
                    {
                        res = true;
                    }
                }
                else
                {
                    System.out.println("False");
                    res = false;
                }
            }
        }

        return res;
    }

    public static boolean CheckMove()
    {
        if(TurnManager.getFirstCountrySelected().GetOwnerId() != TurnManager.CurrentPlayer.getPlayerID() || TurnManager.getSecondCountrySelected().GetOwnerId() != TurnManager.CurrentPlayer.getPlayerID())
        {
            return false;
        }
        else
        {
            return CheckConnection(TurnManager.getFirstCountrySelected(), TurnManager.getSecondCountrySelected());
        }
    }

    public static void CleanCheck()
    {
        for(int i = 0; i < Map.countries.size();i++)
        {
            Map.countries.get(i).SetChecked(false);
        }
    }
}