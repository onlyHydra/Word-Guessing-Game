# Word-Guessing-Game


This is a 1 player game in which with the 3 letters give by the server the player must guess words made only from those letter.
The app has the following features:

Login : User logs in using his account created . If the account is verifed the app will then procced on requesting a new game to the server.
Upon succesfull login the app changes views to the game table. 
Guessing Word : There is a text field in which the user can introduce a word made by the 3 letters given by the server. THe words can have repeated letters.
There are 3 round of game in total ,each round consisting of the user trying to guess the words. Upon succesfull guess the user is awared points in value of the lenght of
the word guessed. If the user didn't manage to guess the word his points will be calculated as following:
Let's assume the words in question are : "aer,are,ar". We are in the 2nd round of the game . In the first round the user succefully guessed the word "are" and was awarded
3 points. Now there are only left the following words : "aer,ar" the user typed the word "ae"  , and failed to guess. For each letter's position matching the position of 
one of the words that needed to be guessed there will be 1 point awarded. If there are multiple words that the position match the cumulative score of each word will be
compared and the highest score will be awarded : for example "bere, eclere" typed in "ere" , for bere we get 3points , for eclere we get 3 points , for the final round
score would be 3 form either bere or eclere


The application is a client-server arhitecture made in java using rpc protocols and sockets.
Server is built from ground up , the protocols are hand made . Data transfers are made through requests and worker thread ,I am using DTOs to communicate.
The application has database for data storage, and ORM for pressitance .
Client  GUI is made using JavaFX.
There is a RESTfull API for creating a new game .
