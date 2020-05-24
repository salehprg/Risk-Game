package UI;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.IIOException;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Data {

    public static String GetAppPath()
    {
        try {
            String AppPath = new File(".").getCanonicalPath();
            return AppPath;
        } 
        catch (IOException e) {
            return "";
        }
    }   

    public static List<JLabel> PlayerNameLabels = new ArrayList<JLabel>();
    public static List<JLabel> PlayerSoldierLabels = new ArrayList<JLabel>();
    public static List<JLabel> PlayerCountryCount = new ArrayList<JLabel>();

    public static List<JButton> buttons = new ArrayList<JButton>();
 
    public static int[][] CountryBound = 
    { 
        {30 , 72},          //0
        {73 , 103},          //1  
        {124 , 165},          //2
        {220 , 70},          //3
        {76 , 152},          //4
        {171 , 160},          //5
        {68 , 216},          //6
        {140 , 240},          //7
        {126 , 330},          //8
        {195 , 380},          //9
        {255 , 427},          //10
        {220 , 482},          //11
        {377 , 306},          //12
        {444 , 309},          //13
        {428 , 386},          //14
        {469 , 390},          //15
        {440 , 447},          //16
        {497 , 436},          //17
        {330 , 112},          //18
        {420 , 110},          //19
        {367 , 162},          //20
        {414 , 171},          //21
        {370 , 212},          //22
        {419 , 204},          //23
        {455 , 155},          //24
        {539 , 116},          //25
        {581 , 94},          //26
        {642 , 56},          //27
        {704 , 55},          //28
        {638 , 125},          //29
        {634 , 184},          //30
        {603 , 224},          //31
        {567 , 266},          //32
        {620 , 278},          //33
        {486 , 265},          //34
        {702 , 215},          //35
        {524 , 193},          //36
        {651 , 358},          //37
        {716 , 366},          //38
        {684 , 442},          //39
        {724 , 444},          //40
        {755 , 503},          //41
        
    };

    public static String[] CountryName = 
    {
        "NAmerica.Alaska",
        "NAmerica.Northwest territory",
        "NAmerica.Ontario",
        "NAmerica.Green land",
        "NAmerica.Alberta",
        "NAmerica.Quebec",
        "NAmerica.Western united states",
        "NAmerica.Eastern united states",
        "NAmerica.Central America",
        "SAmerica.Venezuela",
        "SAmerica.Brazil",
        "SAmerica.Argentina",
        "Africa.Argentina",
        "Africa.Egypt",
        "Africa.Congo",
        "Africa.East Africa",
        "Africa.South Africa",
        "Africa.Madagascar",
        "Europe.Iceland",
        "Europe.Scandinavia",
        "Europe.Great Britain",
        "Europe.Northern Europe",
        "Europe.Western Europe",
        "Europe.Southern Europe",
        "Europe.Ukraine",
        "Asia.Ural",
        "Asia.Siberia",
        "Asia.Yakutsk",
        "Asia.Kamchatka",
        "Asia.Irkutsk",
        "Asia.Mongolia",
        "Asia.China",
        "Asia.India",
        "Asia.Siam",
        "Asia.Middle east",
        "Asia.Japan",
        "Asia.Afghanistan",
        "Australia.Indonesia",
        "Australia.New guinea",
        "Australia.Western Australia",
        "Australia.Eastern Australia",
        "Australia.lotR",
        
    };


    //در ایدی همسایه های هر کشور ردیف های فرد ایدی کشور اصلی و ردیف بعدی آن یعنی ردیف زوج ایدی همسایه ها میباشد
    public static int[][] NeighbourId = 
    {
        {0} ,
        {1 , 4 , 28},
        {1} ,
        {0 , 4 , 2 , 3},
        {2},
        {3 , 5 , 7 , 6 , 4 , 1},
        {3} ,
        {18 , 5 , 2 , 1} ,
        {4} ,
        {0 , 1 , 6 , 2} ,
        {5} ,
        {3 , 2 , 7} ,
        {6} ,
        {4 , 2 , 7 , 8} ,
        {7} ,
        {5 , 2 , 6 } ,
        {8} ,
        {6 , 9} ,
        {9} ,
        {10 , 11 , 8} ,
        {10} ,
        {11 , 9 , 12} ,
        {11} ,
        {10 , 9} ,
        {12} ,
        {10 , 13 , 14 , 23 , 22} ,
        {13} ,
        {12 , 14 , 15 ,34 , 23} ,
        {14} ,
        {12 , 13 , 15 ,16} ,
        {15} ,
        {13 , 14 , 16 , 17} ,
        {16} ,
        {14 , 15 , 17} ,
        {17} ,
        {15 , 16} ,
        {18} ,
        {3 , 20} ,
        {19} ,
        {24 , 20} ,
        {20} ,
        {18 , 19 , 21 , 22} ,
        {21} ,
        {20 , 22 , 23 , 24} ,
        {22} ,
        {20 , 21 , 23 , 12} ,
        {23} ,
        {12 , 13 , 22 , 24 , 21} ,
        {24} ,
        {19 , 21 , 23 , 34 , 36 , 25} ,
        {25} ,
        {24 , 26 , 36} ,
        {26} ,
        {25 , 36 , 31 , 30 , 29 , 27 } ,
        {27} ,
        {26 , 29 , 28} ,
        {28} ,
        {27 , 29 , 35 , 30 , 0} ,
        {29} ,
        {26 , 27 , 28 , 30} ,
        {30} ,
        {26 , 29 , 28 , 35 , 31} ,
        {31} ,
        {30 , 26 , 36 , 32 , 33} ,
        {32} ,
        {34 , 36 , 31 , 33} ,
        {33} ,
        {31 , 32 , 37} ,
        {34} ,
        {13 , 32 , 36 , 24} ,
        {35} ,
        {28 , 30} ,
        {36} ,
        {24 , 25 , 26 , 31 , 32 , 34} ,
        {37} ,
        {33 , 38 , 39} ,
        {38} ,
        {37 , 39 , 40 } ,
        {39} ,
        {37 , 38 , 40} ,
        {40} ,
        {38 , 39 , 41} ,
        {41} ,
        {40} ,
     
    };
}