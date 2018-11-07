import java.util.*;
public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> DotComList = new ArrayList<>();
    private int NumOfGuesses = 0;
    private int gridLength = 7;
    private int gridSize = gridLength*gridLength;

    //метод подготовки параметров игры
    private void setUpGame(){
        //создадим несколько сайтов и присвоим им имена адресов
        DotCom DotCom1 = new DotCom();//Создаем обьект сайта
        DotCom1.setDotComName("Pornhub.com");//Назначаем ему параметр name
        DotCom DotCom2 = new DotCom();
        DotCom2.setDotComName("Youporn.com");
        DotCom DotCom3 = new DotCom();
        DotCom3.setDotComName("Microsoft.com");
        //помещаем созданные обьекты сайтов в блок обьектов DotComList
        DotComList.add(DotCom1);//добавляем обьект DotCom1
        DotComList.add(DotCom2);
        DotComList.add(DotCom3);
        //получаем обратно от обьектов DotCom их параметр name
        String DotCom1Name = DotCom1.getDotComName();
        String DotCom2Name = DotCom2.getDotComName();
        String DotCom3Name = DotCom3.getDotComName();
        //инструкции игроку
        System.out.println("Ваша цель - потопить три \"сайта\":");
        System.out.print(DotCom1Name+", "+DotCom2Name+", "+DotCom3Name+" ");
        System.out.println("- за минимальное количество ходов.");
        System.out.println("Каждый из сайтов занимает 3 ячейки горизонтально");
        System.out.println("и располагается на поле вида:");

        //Рисуем условное поле
        String GridHor = "abcdefg";
        System.out.print("   ");
        for (int i=0; i<gridLength;i++){ //Рисуем строку столбцов из букв
            char c = GridHor.charAt(i);
            System.out.print("  "+c+" ");
        }
        System.out.println();
        int j=0;
        System.out.print(j+"\t");
        for (int i = 0; i < gridSize; i++) {
            if (i > 0 && i % gridLength == 0) {
                j++;
                System.out.println("\t");
                System.out.print(j+"\t"); //выводит номер строки
            }
            //System.out.print(i+"\t"); //выводит порядковые номера ячеек в сетке
            System.out.print("|_|"+"\t"); //выводит сетку
        }
        System.out.println();
        System.out.println("Удачи!");

        //для каждого обьекта DotCom в списке
        for (DotCom dotComToSet:DotComList){
            ArrayList<String> newLocation = helper.placeDotCom(3);//команда загадать адрес сайта
            dotComToSet.setLocationCells(newLocation);//передаем адрес этому сайту
        }
    }

    //метод получения ходов игрока и отправка их на проверку
    private void startPlaying(){
        while(!DotComList.isEmpty()){//до тех пор пока не "потопятся" все сайты,т.е. список не будет пуст
            String userGuess = helper.getUserInput("Сделай ход");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    // метод проверки ходов игрока
    private void checkUserGuess(String userGuess){
        NumOfGuesses++;
        String result = "Мимо";
        for(DotCom dotComToTest:DotComList){//для каждого сайта проверяем на попадание или утопление
            result = dotComToTest.checkYourself(userGuess);//отправляем обьект на проверку попаданий
            if(result.equals("Попал"))
                break;
            if(result.equals("Потопил")){
                DotComList.remove(dotComToTest);//если сайт потоплен то удаляем его из списка
                break;
            }
        }
        System.out.println(result);//показываем игроку результат хода
    }

    //метод завершения игры
    private void finishGame(){
        System.out.println("Все сайты потоплены за "+NumOfGuesses+" попыток!");
        if (NumOfGuesses==9)
            System.out.println("Это просто везение!");
        else if (NumOfGuesses>9 && NumOfGuesses<18)
            System.out.println("Отличный результат =)");
        else
            System.out.println("Неплохой результат! Поработайте над точностью.");
    }

    //главный метод программы
    public static void main (String [] args){
        DotComBust game = new DotComBust();//создаем игровой обьект
        game.setUpGame();//подготавливаем параметры игры
        game.startPlaying();//запускаем игру
    }
}

