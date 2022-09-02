DROP DATABASE IF EXISTS guessthenumberdb;
CREATE DATABASE guessthenumberdb;

USE guessthenumberdb;

CREATE TABLE game(
gameid INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(100)
);

CREATE TABLE meeting(
gameId INT PRIMARY KEY AUTO_INCREMENT,
gameAnswer VARCHAR(4) NOT NULL,
gameStatus VARCHAR(15) NOT NULL;

CREATE TABLE round(
roundId INT PRIMARY KEY AUTO_INCREMENT,
guess VARCHAR(4) NOT NULL,
guessTime TIMESTAMP NOT NULL),
roundResult VARCHAR(10);

CREATE TABLE game_round(
gameId INT NOT NULL,
roundId INT NOT NULL,
PRIMARY KEY(gameId, roundId),
FOREIGN KEY (gameId) REFERENCES game(gameId),
FOREIGN KEY (roundId) REFERENCES round(roundId));