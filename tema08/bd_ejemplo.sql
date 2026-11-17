SET NAMES utf8mb4;
CREATE DATABASE IF NOT EXISTS pmdm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pmdm;

DROP TABLE IF EXISTS videojuegos;
CREATE TABLE videojuegos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    imagen VARCHAR(500) NOT NULL
);

INSERT INTO videojuegos (titulo, plataforma, imagen) VALUES
('Call of Duty: Mobile', 'Móvil', 'https://upload.wikimedia.org/wikipedia/commons/3/37/Call_of_Duty_Mobile_2023_logo.svg?utm_source=es.wikipedia.org&utm_campaign=index&utm_content=original'),
('Free Fire', 'Móvil', 'https://upload.wikimedia.org/wikipedia/en/c/c5/Logo_of_Garena_Free_Fire.png'),
('Grand Theft Auto V', 'PlayStation 5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpDu26g5Va19L-fmlWrB-W_oB9UVGMIjF2ESfJLl_F7Xg6ZqEcJ0esXwg&s=10'),
('Minecraft', 'PC', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFUhPegzs1F9yXFKrwmECfNJrypdDCIyE7xw1Jv4Uusg&s=10'),
('Mobile Legends', 'Móvil', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToannxbTrPbAZsoXWdQR2Dq5T68ExnCh_CtoP-Cvv2PQ&s'),
('PUBG: Battlegrounds', 'PC', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDRJvtCgq6p4shtiN5745pW_SlkpIp39mwkwTkikN4pg0KD8WDomMFrGlq&s=10'),
('Roblox', 'PC', 'https://thumb.wikimedia.org/wikipedia/commons/thumb/f/f2/Roblox_%282025%29_%28App_Icon%29.svg/250px-Roblox_%282025%29_%28App_Icon%29.svg.png?utm_source=es.wikipedia.org&utm_campaign=parser&utm_content=thumbnail'),
('Valorant', 'PC', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8qLNClC4nl43RXtcpB3nJRzSkPVJCo5N5NTCdHP9KoQ&s=10');