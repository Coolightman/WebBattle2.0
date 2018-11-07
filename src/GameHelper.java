import java.io.*;
import java.util.*;

public class GameHelper{

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int [gridSize];

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphaCells = new ArrayList<>(); // блок для итоговых значений ячеек сайта
        int [] cords = new int [comSize]; //массив координат сайта
        int attempts = 0; //счетчик текущих попыток
        boolean success = false; //нашли ли подходящее местоложение?
        int location; //начальное местоположение

        while (!success & attempts++ <200){ //цикл поиска мест размещения сайтов, повторяется
            // пока НЕ успех и попыток меньше 200
            location = (int)(Math.random()*gridSize); //получаем случайную стартовую точку
            int x = 0; // i-я ячейка сайта, которую пытаемся разместить
            success = true; //пусть попытка выбора ячейки успешна

            while(success && x < comSize){ //цикл проверки размещения каждого элемента сайта,
                //выполняется пока попытка успешна
                //и размещаемый элемент не больше длины сайта
                cords[x] = location; //i-ый элемент занимает текущее рандомное значение
                if(grid[location]==0){ //если ячейка еще не занята
                    if (location >= gridSize){ //НЕ успех при выходе за общее число ячеек от 0 до 48
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)){ //НЕ успех если перешли за правый край сетки
                        success = false;
                    }
                }
                else{ //НЕ успех если ячейка занята
                    success = false;
                }
                x++;
                location++;
            }
        }

        // перевод координат положения ячеек сайта в символьные
        int x = 0;
        while (x<comSize)            {
            grid[cords[x]] = 1; // помечаем что ячейка уже занята
            int row = (cords[x] / gridLength); // получаем номер ряда
            int column = cords[x] % gridLength; // получаем номер колонки
            String finalColumn = String.valueOf(alphabet.charAt(column)); // переводим номер колонки в соотв. букву
            String finalRow = Integer.toString(row); // переводим номер строки в строковое значение
            String finalCords = finalColumn.concat(finalRow); // обьединяем значения колонки и строки
            alphaCells.add(finalCords); // добавляем координаты в блок итоговых координат ячеек сайта
            x++;
        }

        //показать итоговое размещение сайта
        System.out.print("Адрес сайта: "+alphaCells.get(0)+" "+alphaCells.get(1)+" "+alphaCells.get(2)+" или ");
        System.out.print("Ячейки № ");
        for (int i:cords){
            System.out.print(i+" ");
        }
        System.out.println();
        return alphaCells; //возвращаем ответ метода, т.е. блок итоговых координат сайта
    }

    //метод получения значения введенного игроком
    public String getUserInput(String prompt){

        String inputLine = null;
        System.out.println(prompt + " ");
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if(inputLine.length()==0)
                return null;
        }
        catch (IOException e){

            System.out.println("IOException: "+e);
        }
        return inputLine;
    }
}
