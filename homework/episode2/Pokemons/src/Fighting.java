import java.util.Scanner;

/**
 * Created by mihailpoplavkov on 10.11.2016.
 */
public class Fighting {
    public static void main(String[] args) {
        System.out.println(
                "░█▀▀▄░░░░░░░░░░░▄▀▀█\n" +
                "░█░░░▀▄░▄▄▄▄▄░▄▀░░░█\n" +
                "░░▀▄░░░▀░░░░░▀░░░▄▀\n" +
                "░░░░▌░▄▄░░░▄▄░▐▀▀\n" +
                "░░░▐░░█▄░░░▄█░░▌▄▄▀▀▀▀█\n" +
                "░░░▌▄▄▀▀░▄░▀▀▄▄▐░░░░░░█\n" +
                "▄▀▀▐▀▀░▄▄▄▄▄░▀▀▌▄▄▄░░░█\n" +
                "█░░░▀▄░█░░░█░▄▀░░░░█▀▀▀\n" +
                "░▀▄░░▀░░▀▀▀░░▀░░░▄█▀\n" +
                "░░░█░░░░░░░░░░░▄▀▄░▀▄\n" +
                "░░░█░░░░░░░░░▄▀█░░█░░█\n" +
                "░░░█░░░░░░░░░░░█▄█░░▄▀\n" +
                "░░░█░░░░░░░░░░░████▀\n" +
                "░░░▀▄▄▀▀▄▄▀▀▄▄▄█▀");

        System.out.println("");
        Pokemon poki1 = new Pokemon("Pikachu", 40);
        Pokemon poki2 = new Pokemon("Bulbasaur", 30, poki1);
        poki1.setEnemy(poki2);
        poki1.setState(Pokemon.State.FIGHTING);
        poki2.setState(Pokemon.State.FIGHTING);

        while (poki1.isAlive() && poki1.hasEnergy() && poki2.isAlive() && poki2.hasEnergy()) {
            getCommands(poki1);
            getCommands(poki2);
            poki1.go();
            poki2.go();
        }
    }

    private static void getCommands(Pokemon pokemon) {
        Scanner scanner = new Scanner(System.in);
        Pokemon.Combat combat = null;
        Pokemon.Direction direction = null;
        System.out.println(pokemon.getName() + "'s actions (type \"attack\" or \"a\" to attack and \"defend\" or \"d\" to defend. Then type direction (\"head\"/\"h\", \"body\"/\"b\" or \"feet\"/\"f\")):");
        boolean success = true;
        do {
            if (!success) {
                System.out.println("Wrong arguments. Please, repeat.");
            }
            success = false;
            String[] actions = scanner.nextLine().split(" ");
            if (actions.length != 2) {
                continue;
            }
            String tmp = actions[0].toLowerCase();
            switch (tmp) {
                case "a":
                case "attack":
                    combat = Pokemon.Combat.ATTACK;
                    break;
                case "d":
                case "defend":
                    combat = Pokemon.Combat.DEFEND;
                    break;
                default:
                    continue;
            }

            tmp = actions[1].toLowerCase();
            switch (tmp) {
                case "h":
                case "head":
                    direction = Pokemon.Direction.HEAD;
                    break;
                case "b":
                case "body":
                    direction = Pokemon.Direction.BODY;
                    break;
                case "f":
                case "feet":
                    direction = Pokemon.Direction.FEET;
                    break;
                default:
                    continue;
            }
            success = true;
        } while (!success);
        pokemon.setAction(combat, direction);
    }
}
