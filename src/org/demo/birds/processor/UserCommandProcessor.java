package org.demo.birds.processor;

import org.demo.birds.entities.Bird;
import org.demo.birds.store.BirdStore;

import java.util.*;

/**
 * Реализовать интерфейсы IBirdCreator, IUserCommandProcessor, IInfiniteLoopProcessor
 */
public class UserCommandProcessor implements IBirdCreator, IUserCommandProcessor, IInfiniteLoopProcessor
{
    private static final String ADD_BIRD_COMMAND = "a";
    private static final String SEARCH_BIRD_BY_NAME_COMMAND = "s";
    private static final String SEARCH_BIRDS_BY_AREA_COMMAND = "l";
    private static final String EXIT_COMMAND = "exit";

    @Override
    public void processInLoop()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMainMenu();
            String userCommand = getUserCommandInMainMenuFromConsole(scanner);
            if (userCommand == null)
            {
                continue;
            }
            if (userCommand.equalsIgnoreCase(EXIT_COMMAND))
            {
                break;
            }
            processUserCommand(userCommand, scanner);
        }
        System.out.println("Exiting...");
    }

    @Override
    public void processUserCommand(String command, Scanner userInputReader)
    {
        BirdStore birdStore = BirdStore.getInstance();
        switch (command)
        {
            case ADD_BIRD_COMMAND:
            {
                Bird bird = createBird(userInputReader);
                birdStore.addBird(bird);
                break;
            }

            case SEARCH_BIRD_BY_NAME_COMMAND:
            {
                Bird foundBird = birdStore.searchByName(getNonEmptyStringFromUser("bird"));
                if (foundBird == null)
                {
                    System.out.println("No bird found");
                }
                else
                {
                    System.out.println("Found bird:\n" + foundBird.toString());
                }
                break;
            }

            case SEARCH_BIRDS_BY_AREA_COMMAND:
            {

                List birdsInLivingArea = birdStore.searchByLivingArea(getNonEmptyStringFromUser("living area"));
                if (birdsInLivingArea == null)
                {
                    System.out.println("No birds found in this area.\n");
                }
                else
                {
                    System.out.println(Arrays.toString(birdsInLivingArea.toArray()));
                }
                break;
            }
        }
    }

    private static String getUserCommandInMainMenuFromConsole(Scanner scanner)
    {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase(ADD_BIRD_COMMAND) ||
            input.equalsIgnoreCase(SEARCH_BIRD_BY_NAME_COMMAND) ||
            input.equalsIgnoreCase(SEARCH_BIRDS_BY_AREA_COMMAND) ||
            input.equalsIgnoreCase(EXIT_COMMAND))
        {
            return input;
        }
        else
        {
            System.out.println("No such commands.\n");
            return null;
        }
    }

    private static void printMainMenu()
    {
        System.out.println("Please, enter command:");
        System.out.println("\ta - add new Bird");
        System.out.println("\ts - search bird by name");
        System.out.println("\tl - search bird by living area");
        System.out.println("\texit - terminate application");
    }

    @Override
    public Bird createBird(Scanner userInputReader)
    {
        String birdName = getNonEmptyStringFromUser("name");
        String birdLivingArea = getNonEmptyStringFromUser("living area");
        double birdSize = getPositiveNumberFromUser("size");
        return new Bird(birdName, birdLivingArea, birdSize);
    }

    private static String getNonEmptyStringFromUser(String purpose)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter " + purpose);
        while (true)
        {
            String input = scanner.nextLine();
            if (input.isEmpty())
            {
                System.out.println("Enter non-empty name. Try once more");
                continue;
            }
            return input;
        }
    }

    private static Double getPositiveNumberFromUser(String purpose)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter positive number for " + purpose);
        while (true)
        {
            String input = scanner.nextLine();
            double value;
            if (input.isEmpty())
            {
                System.out.println("Enter something. Try once more");
                continue;
            }
            else
            {
                try
                {
                    value = Double.parseDouble(input);
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Please enter a number");
                    continue;
                }

                if (value <= 0)
                {
                    System.out.println("Please enter positive number");
                    continue;
                }
            }
            return value;
        }
    }
}
