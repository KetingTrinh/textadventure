/*
 * Keting Trinh
 * ICS3UE
 * Wednesday August 10 2022
 * This program creates an Adventurer and a Slime object used to simulate battles in the text adventure program
 */

import java.util.Random; // imports Random class to generate random values
import java.util.*; // imports scanner

/**
 *
 * @author trinh
 */
public class Adventurer {
    // public variables are defined so that they can be manipulted in the main program
    public double attack;
    public double defense;
    public double speed;
    final public double FULL_HEAL = 4; // default full health value
    public boolean hasScanner = false; // has chosen the Scanner path
    public double cryBonus = 0; // has chosen the battle cry path
    public boolean canCry = false;
    public String weapon;
    public String role; // available to other classes as objectName.role
    public boolean battleWon; // reflects whether a user has won or lost a battle
    
    public Scanner input = new Scanner(System.in); // used to get user input 
    public String moveChoice; // stores whether a user attacks or escapes
    
    // constructor, object is initialised with parameter values
    public Adventurer(int _weapon){
        switch(_weapon){// depending on the weapon chosen at the beginning of the game, variables are given different values
            case 1: // user chose blade
                attack = 2;
                defense = 1;
                speed = 3;
                role = "SWORDSMAN";
                weapon = "BLADE";
                break;
            case 2: // user chose axe
                attack = 3;
                defense = 2;
                speed = 2;
                role = "LUMBERJACK";
                weapon = "AXE";
                break;
            case 3: // user chose sheild
                attack = 1;
                defense = 3;
                speed = 1;
                role = "TANKER";
                weapon = "SHEILD";
                break;
        }
    }
    
    public void beginBattle(String _ability, Slime _slime){
        battleWon = false; // resets battleWon before every battle
        // checks if user has inputted the correct ability and applies it
        if (_ability.toUpperCase().equals("GRASS") && canCry == true){ // user types grass and has the ability
            System.out.println("You used battle cry! Your next attack will deal +1 damage");
            cryBonus = 1;
        }
        else if (_ability.toUpperCase().equals("SCAN") && hasScanner == true){ // user types scan and has the ability
            System.out.println("You used the scanner! You see the slime's stats\nAttack: " + _slime.attack + "\tDefense:" + _slime.defense + "\tSpeed: " + _slime.speed);
        }
        else {// if user enters any other value it does not have access to
            System.out.println("You are trying to use moves you cannot comprehend");
        }
        
        // first attack
        if (_slime.speed <= this.speed){//if user is faster or as fast as the slime
            // prompts the user
            System.out.println("Make a move before the slime reacts Adventurer! Type in your choice\nATTACK\tESCAPE");
            moveChoice = input.nextLine();
            //values are subtracted from slime object
            if (moveChoice.toUpperCase().equals("ATTACK")){//user decides to attack
                System.out.println("You attack the slime using your " + this.weapon + "!");
                _slime.defense = _slime.defense - this.attack - cryBonus;
                if (_slime.defense <= 0){ // if slime defense falls below zero within the first attack
                    _slime.isBattleOver("you 'One Hit Knocked Out' the slime!");
                    battleWon = true; // user has won battle
                }
                else {//slime is still alive
                    System.out.println("The slime is stil alive!");
                    // slime "attacks" user, slime attack values are subtracted from the user's defense
                    System.out.println("The slime shoots you with its gun and deals " + _slime.attack + " damage to you!");
                    this.defense = this.defense - _slime.attack;
                    
                    if (this.defense >= 0){ // if the user's defense is equal to 0 or greater, user is still in battle
                        System.out.println("But you're still alive!");
                    }
                    else {// user is one hit knocked out by slime
                        _slime.defense = 0;
                        _slime.isBattleOver("you got 'One Hit Knocked Out' by a slime!");// slime object calls the isBattleOver method, following statements will not run, battleWon remains false
                    }
                }
            }
            else if (moveChoice.toUpperCase().equals("ESCAPE")){ //if user decides to escape, isBattleOver method called, following statements will not run, battleWon remains false
                _slime.defense = 0;
                _slime.isBattleOver("you escaped");
            }
        }
        else{// if slime is faster than user
            System.out.println("The slime is a quick sniper, it shoots you with its gun and deals " + _slime.attack + " damage to you!");
            this.defense = this.defense - _slime.attack;
            if (this.defense >= 0){
                System.out.println("But you're still alive!");
            }
            else {// user is one hit knocked out by slime
                _slime.defense = 0;
                _slime.isBattleOver("you got 'One Hit Knocked Out' by a slime!");
            }
        }
        cryBonus = 0;//bounus that was set for the first turn only is reset to zero
        
        midBattle ://label to prevent forever loop
        while (_slime.inBattle){ // while the the slime's health is above zero
            System.out.println("The slime is still alive!");
            System.out.println("Make a move Adventurer! Type in your choice\nATTACK\tESCAPE");
            moveChoice = input.nextLine();
            // user is prompted for a move
            if (moveChoice.toUpperCase().equals("ATTACK")){ // user choses to attack
                System.out.println("You attack the slime using your " + this.weapon + "!");
                // user's attack is subtracted from the slime's defense
                _slime.defense = _slime.defense - this.attack;
                if (_slime.defense <= 0){ // if the slime's defense is equal to or lower than 0
                    _slime.isBattleOver("you knocked out the slime!"); // battle is over
                    battleWon = true; // battleWon is set to true
                    break midBattle; // breaks out of while loop, following statements do not run
                }
            }
            else if (moveChoice.toUpperCase().equals("ESCAPE")){ // user decides to escape
                _slime.defense = 0; // 
                _slime.isBattleOver("you escaped"); // isBattleOver ends the battle
                break midBattle; // breaks out of while loop, following statements do not run
            }
            
            //next section runs only if slime is still alive and the user has not escaped yet
            if (_slime.defense > 0){ // if the slime's defense is greater than zero
                System.out.println("The slime is still alive!");
                System.out.println("The slime shoots you with its gun and deals " + _slime.attack + " damage to you!");
                // slime attack is subtracted from user defense
                this.defense = this.defense - _slime.attack;
                
                if (this.defense >= 0){ // if user defense is greater than or equal to zero, loop continues
                    System.out.println("But you're still alive!");
                }
                else {// user is one hit knocked out by slime
                    _slime.defense = 0;
                    _slime.isBattleOver("you got knocked out by a slime!");
                    break midBattle; // breaks out of loop, isBattleOver ends the battle
                }
            }
        }
    }
    
    public void fullHeal(int _extra_heal){ // resets the user's defense the value stored in FULL_HEALTH plus any specified bonus health
        defense = FULL_HEAL + _extra_heal;
        System.out.println("Your health has been restored!");
    }
}

class Slime { // creates Slime objects
    // Random generator is created
    Random stat_generator = new Random();
    // strength values are randomly generated and initialised
    public double attack = stat_generator.nextInt(3) + 1;
    public double defense = stat_generator.nextInt(3) + 1;
    public double speed = stat_generator.nextInt(3) + 1;
    // is set to reflect whether the battle is over or not to control the while loop in the beginBattle() method of the Advenenturer class
    boolean inBattle = true;
    
    // ends the battle and displays message to the user depending on how it ended
    public void isBattleOver(String _reason){
        if (defense <= 0){ // if the defense is less than or equal to zero, the slime is no longer in battle
            inBattle = false;
            System.out.println("The battle is over because " + _reason);
        }
    }
}