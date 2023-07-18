/*
 * Keting Trinh
 * ICS3UE
 * Wednesday August 10 2022
 * This program takes the user on a choose your own adventure!
 */

import java.util.*;

/**
 *
 * @author trinh
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // imports scanner
        String name; // stores user's name
        int userAction; // stores the user's choice
        String userAbility; // stores the ability a user has during battle
        
        // Prompts user with name through a narritive character
        System.out.println("Ant : GREETINGS ADVENTURER! WHAT IS YOUR NAME?");
        System.out.print("YOU {enter your name} : ");
        // gets user's name, converts to uppercase
        name = input.nextLine().toUpperCase();
        
        // prompts user to pick 1 of 3 options. Depending on the one they pick, they will have different strength statistics
        System.out.println("Ant : WE DON'T HAVE MUCH TIME " + name +"! PICK UP A WEAPON!");
        System.out.println("""
                           ==============================================================
                           In front of you on the ground, you see three weapons.
                           Equip your weapon of choice by typing in its number
                           
                           1: BLADE
                           2: AXE
                           3: SHEILD
                           """);
        // stores the user's choice
        userAction = input.nextInt();
        // creates a new Adventurer object, armed with the weapon of the user's chioce (values are initialiesed with different values depending on which weapon)
        Adventurer user = new Adventurer(userAction);
        
        // comments on the user's weapon choice
        System.out.println("\nAnt : A NATURAL " + user.role + " I SEE!");
        
        // PROMPTS THE USER TO PICK AN ACTION
        System.out.println("""
                           Ant : THE JAVA KINGDOM HAS BEEN BESEIGED BY MONSTERS!
                           Ant : THE SLIMES HAVE GUNS NOW, WHAT DOES THE ADVENTURER SUGGEST WE DO?
                           """);
        System.out.println("=============================================================="
                + "\nWell " + name + ", it's time to make a choice. What will you do?");
        System.out.println("""
                           Make your decision by typing in its number
                           
                           1: Ask for more information
                           2: Explore
                           """);
        
        // user picks an action
        userAction = input.nextInt();
        
        
        if (userAction == 1){ // if user decides to ask for more information, provided diaologue
            System.out.println("""
                               Ant : IT SEEMS THAT AFTER THE DIMENSIONAL RIFT, THE WORLD HAS BEEN OVERRUN BY SLIMES!
                               YOU : The dimensional WHAT?!
                               Ant : HERE TAKE THIS SCANNER
                               
                               You aquired a Slime Scanner! Type in "SCAN" at the beginning of a battle to see a slime's stats! Type OK to continue""");
            input.next(); // used for pacing purposes, engage and not overwhelm the user
            System.out.println("""
                               YOU : What does this do?
                               Ant : IT SHOWS YOU THE SLIME'S STRENGTH! WE'RE GOING TO SEND YOU IN TO ATTACK THEM!
                               YOU : I have to WHAT?!
                               Ant : SLAY ADVENTURER SLAY!""");
            user.hasScanner = true; // sets the hasScanner variable of the Adventurer object to true so the user can use it in a later battle
        }
        else if (userAction == 2){// if the user decides to explore, provided diaologue
            System.out.println("""
                               Ant : DO YOU SUSPECT THAT THERE'D BE ANY SLIMES NEAR HERE? I HEAR THEY LOVE GRASS
                               
                               The two of you start to walk around the forest. You spot a slime nibbling on some prestine grass
                               
                               YOU : GRASS!                               
                               """);
            System.out.println("It notices you, and attacks your party. You pull out your " + user.weapon + " and attack the creature."
                    + "\nYou lunge... CRITICAL HIT! Turns out, yelling grass distracts the slimes long enough to deal extra damage!"
                    + "\n\nYou learned BATTLE CRY! Type in \"GRASS\" at the beginning of a battle to deal +1 damage on your first attack! Type OK to continue");
            user.canCry = true; // sets the canCry variable of the Adventurer object to true so the user can use it in a later battle
            input.next(); // used for pacing purposes, engage and not overwhelm the user
            
        }
        
        // A battle has begun where the user can interact with a randomly generated Slime object
        System.out.println("Ant : LOOK OVER THERE! IT'S A SLIME!\nAnt : GO " + name + "! I CHOOSE YOU!");
        // prompts the user to enter the ability mentioned in the previous choice
        System.out.println("""
                           ==============================================================
                           You heard the man, it's time to battle!
                           Use your new ability! Type it here""");
        // assigns user's choice to userAbility
        userAbility = input.next();
        // a new enemy Slime object is created, and generated with random values
        Slime slime_one = new Slime();
        // beginBattle method is called, given the user's ability and the slime user is going up against (to see more detail, look at the beginBattle method of the Adventurer class)
        user.beginBattle(userAbility, slime_one);
        
        // After the battle concludes nothing changes besides the text shown to user (which is dependant on the outcome of the battle)
        if (user.battleWon){ // if user won battle,this text is shown
            System.out.println("The slime dropped FULL HEAL GRASS! You immediately wolf it down.");
            user.fullHeal(0); // this method is used to reset the user's health
            System.out.println("Ant : YOU DID IT " + name + "! (Type OK to continue)");
            input.next(); // to engage and pace the game
        }
        else if (!user.battleWon){ // if user lost battle, this text is shown
            System.out.println("Ant : WELL, YOU TRIED YOUR BEST, TAKE THIS FULL HEAL GRASS! IT'S MY LAST ONE, GET YOUR STRENGTH BACK!");
            user.fullHeal(0); // this method is used to rest the user's health
            System.out.println("Type OK to continue");
            input.next(); // to engage and pace the game
        }
        
        // PROMPTS THE USER TO GO TO A LOCATION
        System.out.println("Ant : IT'S GETTING LATE, TIME TO FIND SOMEPLACE TO STAY\nAnt : WHERE DO YOU THINK WE SHOULD GO?");
        System.out.println("""
                           ==============================================================
                           The path splits into two. One leads towards a mountain, 
                           surrounded by clouds, the other heads to a paved road.
                           Where would you like to go? Type in the number of your choice
                           
                           1: MOUNTAIN
                           2: ROAD
                           """);
        // stores user's decided action in the userAction variable
        userAction = input.nextInt();
        if (userAction == 1){// user decides to go up the mountain
            System.out.println("""
                               YOU : Let's head up the mountain, we'll have the high ground
                               
                               ==============================================================
                               The two of you head up the mountain, but you don't see anything.
                               Suddenly behind you, you are ambushed by a slime!
                               Time to put those fighting skills to use! Activate your ability!
                               """);
            // a battle has begun where user is prompted to enter their ability
            userAbility = input.next();
            // a Slime object is created, random values are initialised
            Slime mountain_slime = new Slime();
            // the beginBattle method is called, uses the given values, for more detail, go to the beginBattle method of the Adveneturer class
            user.beginBattle(userAbility, mountain_slime);
            System.out.println("Type OK to continue");
            input.next(); // to engage and pace the game
            if(user.battleWon){ // if user won the battle, text is shown
                System.out.println("""
                                   Great! You won! Oh wait, you thought that was it? There's
                                   a whole sworm. You and Ant are outnumbered and thus captured.
                                   Looks like you weren't the only to go for the height advantage.
                                   
                                   === GAME OVER ===
                                   """); // This is Ending 1, program ends here
            }
            else if (!user.battleWon){ // if the user has lost the battle, text is shown
                System.out.println("""
                                   Ant : ADVENTURER! NO!!!
                                   
                                   It's too late, the shock of the attack has sent you off the edge.
                                   You fall, but then land head first into the slime training camps.
                                   Look who's become target practise.
                                   
                                   === GAME OVER ===
                                   """); // This is Ending 2, program ends here
            }
        }
        else if (userAction == 2){// user decides to go along the road
            System.out.println("""
                               YOU : Let's go down the road, it'll probably lead us to civilisation
                               
                               ==============================================================
                               The two of you head towards the road, you can hear something,
                               someone, wheeling a cart down the road.
                               How do you react? Type in the number of your choice
                               
                               1: GO AND ATTACK
                               2: LAY LOW AND OBSERVE
                               """);
            // user is prompted for an action
            // user's action is stored in userAction
            userAction = input.nextInt();
            if (userAction == 1){// user decides to go and attack
                System.out.println("YOU : They might they have supplies, let's rush them\nAnt : YOU GOT IT " + name + "!");
                System.out.println("""
                                   
                                   As a team, you sprint over towards the sound. Its a trio of
                                   slime minions pulling a cart, wearing bullet proof vests.
                                   They probably stole them from the military. 
                                   
                                   Ant : I CAN HANDLE THE BIG GUY, YOU FOCUS ON THE GOONS
                                   
                                   You charge towards the first slime.
                                   
                                   ==============================================================
                                   A battle has begun! Remember to type in your ability!
                                   """);
                // user engages another battle
                // user inputs their given ability
                userAbility = input.next();
                // new Slime object is generated
                Slime slime_two = new Slime();
                // beginBattle method is called
                user.beginBattle(userAbility, slime_two);
                if (user.battleWon){ // if user won battle, following sequence is shown
                    // user is prompted to enter a second battle
                    System.out.println("Time for round two! Careful, you're already a bit tired from the last round. Type in your ability!");
                    userAbility = input.next(); // collects value for user's ability
                    // creates new Slime object
                    Slime slime_three = new Slime();
                    // calls the beginBattle method of the Adventurer class
                    user.beginBattle(userAbility, slime_three);
                    System.out.println("Type OK to continue"); 
                    input.next(); // engages and pases the game
                    
                    if (user.battleWon){// if user won the battle, section is shown
                        System.out.println("""
                                           
                                           As you finish your fight, Ant has successfully tied up their leader. You begin interogation
                                           
                                           SLIME BOSS : Don't look at me, I did it for the money!
                                           SLIME BOSS : Have you SEEN the gas prices lately? I had to get a mortgage on my house, it's crazy
                                           YOU : Lead us to the that town you were talking about and we'll pay you nicely
                                           
                                           So you drop the SLIME BOSS in the back of his cart and head down the road (Type OK to continue)
                                           """);
                        input.next();//pacing, engage user
                        System.out.println("""
                                           You arrive to a desimated town. There is chaos everywhere. But you smell something.
                                           You smell grass, and its comming from the town square.
                                           """);
                        user.fullHeal(1); // resets the user's defense value
                        user.attack = user.attack + 2; // increases the user's attack by two
                        user.speed++; // increases the user's speed by one
                        System.out.println("""
                                           The scent of grass increases all your stats, but the same goes for the slimes!
                                           
                                           YOU : TO THE SQUARE! THAT'S THEIR TARGET!
                                           
                                           ==============================================================
                                           You arrive to the center of the action, and you see the ring leader.
                                           The biggest slime you've ever seen, alongside its bodyguards.
                                           Time for the BOSS BATTLE! Activate your ability!
                                           """);
                        // user is prompted to enter ability
                        userAbility = input.next();
                        // New Slime object is made
                        Slime slime_goon_one = new Slime();
                        // Slime object's values are made to be stronger to make the battle more difficult
                        slime_goon_one.attack = slime_goon_one.attack + 1;
                        slime_goon_one.defense = slime_goon_one.defense + 1;
                        slime_goon_one.speed = slime_goon_one.speed + 1;
                        // beginBattle method is called
                        user.beginBattle(userAbility, slime_goon_one);
                        
                        if (user.battleWon){ // if the user wins the first battle against the first henchmen
                            // user is prompted to enter values for a second battle against the second henchmen
                            System.out.println("Ant : LET'S GO! KEEP AT IT " + name + "!\nTime for the second henchman, get ready for it! Use your ability!");
                            userAbility = input.next();
                            // new Slime object is generated
                            Slime slime_goon_two = new Slime();
                            // Slime object's values are made to be more challenging
                            slime_goon_two.attack = slime_goon_one.attack + 1.5;
                            slime_goon_two.defense = slime_goon_one.defense + 1.5;
                            slime_goon_two.speed = slime_goon_one.speed + 1.5;
                            // beginBattle method is called
                            user.beginBattle(userAbility, slime_goon_two);
                            if (user.battleWon){ // if the user wins the battle against the second henchmen
                                // user is prompted to enter values for a third battle agains the Boss
                                System.out.println("You defeated the second henchman! Watch out! Here comes the mastermind! He's even stronger! Type in your ability!");
                                userAbility = input.next();
                                // new Slime object is generated
                                Slime slime_boss = new Slime();
                                // Slime object's values are made to be even more challenging
                                slime_boss.attack = slime_boss.attack + 2;
                                slime_boss.defense = slime_boss.defense + 2;
                                slime_boss.speed = slime_boss.speed + 2;
                                // beginBattle method is called
                                user.beginBattle(userAbility, slime_boss);
                                if(user.battleWon){ // if user wins all three battles
                                    System.out.println("Type OK to continue");
                                    input.next();
                                    System.out.println("And with that. The slimes ceased fire. The day was saved.\nBut you blank out for exhaustion. (Type OK to continue)");
                                    input.next();
                                    System.out.println("Ant : LOOKS LIKE OUR HERO'S AWAKE!\n\nYou're lying on a bed in the local doctor's office, surrounded by the town's people\n\n"
                                            + "Ant : CHEERS FOR " + name + "! The finest " + user.role + " of the land!"
                                            + "\nEveryone : YOU DID IT " + name + " YOU SAVED THE GAME!"
                                            + "\n\n=== GAME OVER ===");
                                }// user reaches Ending 3, program ends
                            }
                            else if (!user.battleWon){ // killed by the second slime henchmen or by the boss
                                System.out.println("You let everybody down, you got flung so hard you were even spared\n\n=== GAME OVER ===");
                                user.battleWon = true; // avoids triggering the following if statement
                            } // user reaches Ending 4, program ends
                        }
                        else if (!user.battleWon){// killed by the first slime henchemen
                            System.out.println("Ant : " + name + "! YOU CAN'T DIE YET! NO!!!!\n\nAnd you black out...\nType OK to continue");
                            input.next();
                            System.out.println("You find yourself in town jail cell. You were tried and found guilty for physical assult.\nIt turns out attacking the charitable elderly with a " + user.weapon + " is an offense around here\nDon't worry, Ant is stuck in the jail cell next door\n\n=== GAME OVER ===");
                            user.battleWon = true; //avoids triggering the following if statement
                        }// user reaches Ending 5, program ends
                    }
                }
                if (!user.battleWon){//is user loses to one of the two slimes trio on the path
                    System.out.println("""
                                       
                                       It appears that you struggled to defeat the slime. Ant notices this and tries to help you.
                                       But OH NO! The slimes gang up and capture your entire party.
                                       Type OK to continue
                                       """);
                    input.next();
                    System.out.println("You come to in a cubicle, your cubicle. You actually thought you were some hero didn't you?\nGet back to work, slimes don't eat for free\n\n=== GAME OVER ===");
                    // user reaches Ending 6, program ends
                }
                
            }
            else if (userAction == 2){// user decides to observe
                user.speed = user.speed - 1; // user speed goes down by one
                System.out.println("""
                                   YOU : Let's wait it out until the coast is clear
                                   Ant : SOUNDS LIKE A PLAN
                                   
                                   So you wait, you see waves of slime minions pass you by, headed to attack a nearby town.
                                   But you've been stationary for so long, your legs fall alseep
                                   
                                   Your speed is lowered by 1.0!
                                   
                                   Type OK to continue
                                   """);
                input.next();
                System.out.println("""
                                   ==============================================================
                                   You know something will go wrong if you let the slimes take control.
                                   But is it enough to make it your problem? Type in the number of your choice
                                   
                                   1. HELP OUT THE TOWN
                                   2. GO THE OTHER DIRECTION
                                   """);
                // user is prompted to chose where to go
                userAction = input.nextInt();
                if (userAction == 1){ // if usesr decides to go to town
                    System.out.println("""
                                       The two of you walk your way to the town. By the time you arrive
                                       There is a HUGE slime monster wiggling in the town square. You can feel your
                                       stats increase. But is it enough to stop the beast?
                                       ==============================================================
                                       Quick! Use your ability!
                                       """);
                    // user is prompted to battle
                    userAbility = input.next();
                    // user stats are manipulated to go up against the stronger threat
                    user.defense = user.FULL_HEAL + 2;
                    user.attack = user.attack + 2;
                    user.speed = user.speed + 2;
                    // Slime object is created, and values are manipulated to be stronger, but balanced
                    Slime slime_beast = new Slime();
                    slime_beast.defense = slime_beast.defense + 5;
                    slime_beast.attack = slime_beast.defense + 0.5;
                    slime_beast.speed = slime_beast.defense - 2;
                    // beginBattle method is called
                    user.beginBattle(userAbility, slime_beast);
                    System.out.println("Type OK to continue");
                    input.next();
                    if(user.battleWon){ // if uers wins the battle against the slime beast
                        System.out.println("Ant : THAT WAS AWESOME! BUT UH, I THINK WE'RE A BIT TO LATE " + name + "..."
                                + "\nIt turns out, if you let slimes around together long enough, they'll merge, not just with themselves"
                                + "\nbut also with all the townspeople that you were trying to save\n\n=== GAME OVER ==="); // user reaches Ending 7, program ends
                    }
                    else if (!user.battleWon){ // if user loses the battle against the slime beast
                        System.out.println("\nYou, along with Ant, were consumed by the slime beast. It's pretty wet in there.\nDon't suppose you know a way out?\n\n=== GAME OVER ===");
                    }// user reaches Ending 8, program ends
                }
                else if (userAction == 2){// user goes the other direction, away from town
                    System.out.println("""
                                       
                                       YOU : Yeah, let's just go the other way if they're all coming from there
                                       Ant : THAT'S TRUE, THEY'RE NOT GOING BACK, SO THERE WON'T BE ANY SLIMES AROUND
                                       
                                       The two of you head off into the distance, no slime in sight.
                                       You establish a small and happy life with Ant in an abandoned village
                                       
                                       === GAME OVER ===
                                       """);
                }// user reaches Ending 9, program ends
            }
        }
        
    }
    
}
