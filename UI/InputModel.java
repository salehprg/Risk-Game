package UI;

import java.util.ArrayList;

import Map.Country;

public class InputModel {
    public Country FirstCountry;
    public Country SecondCountry;

//#region Deploy
    public int DeploySoldier;
//#endregion

//#region Move
public int MoveSoldier;
//#endregion

//#region War
    public int DefenderSoldier;
    public int AttackerSoldier;

    public ArrayList<Integer> DfndDiceNumber;
    public ArrayList<Integer> AttckDiceNumber;
//#endregion


    public boolean IsExited;
}