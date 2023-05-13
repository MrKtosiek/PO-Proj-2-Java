package WorldSimulator;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        Vector2 worldSize = new Vector2(15,15);
        //System.out.println("Enter world height: ");
        //worldSize.x = scanner.nextInt();
        //System.out.println("Enter world width: ");
        //worldSize.y = scanner.nextInt();

        Game game = new Game(worldSize.x, worldSize.y);

        GameUI gameUI = new GameUI(game);
        gameUI.CreateAndShowWindow("Filip Jezierski 196333", 800, 400);

        gameUI.DrawWorld();
    }

}
