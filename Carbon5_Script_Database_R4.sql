CREATE TABLE car
(
  NumPuce      VARCHAR(100) NOT NULL
    PRIMARY KEY,
  TypeVehicule VARCHAR(50)  NULL,
  matricule    VARCHAR(50)  NULL
)
  ENGINE = InnoDB;

CREATE INDEX fk_typeV
  ON car (TypeVehicule);

CREATE TABLE carddefect
(
  IdDefect INT                    NOT NULL,
  IdCard   INT                    NOT NULL,
  fixed    TINYINT(1) DEFAULT '0' NULL,
  PRIMARY KEY (IdDefect, IdCard)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Card_CardDefect
  ON carddefect (IdCard);

CREATE TABLE cardrepairs
(
  IdRepair INT NOT NULL,
  IdCard   INT NOT NULL,
  PRIMARY KEY (IdRepair, IdCard)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Card_CardRepair
  ON cardrepairs (IdCard);

CREATE TABLE cardstate
(
  Id          INT AUTO_INCREMENT
    PRIMARY KEY,
  Description VARCHAR(50) NULL
)
  ENGINE = InnoDB;

CREATE TABLE defect
(
  Id            INT AUTO_INCREMENT
    PRIMARY KEY,
  Description   TEXT   NULL,
  RepairTime    DOUBLE NOT NULL,
  criticity     INT    NULL,
  partForRepair INT    NULL
)
  ENGINE = InnoDB;

CREATE INDEX fk_part
  ON defect (partForRepair);

ALTER TABLE carddefect
  ADD CONSTRAINT fk_Defect_CardDefect
FOREIGN KEY (IdDefect) REFERENCES defect (Id);

CREATE TABLE operation
(
  id             INT AUTO_INCREMENT
    PRIMARY KEY,
  id_user        INT  NOT NULL,
  difficulte     INT  NOT NULL,
  date_operation DATE NOT NULL,
  temps_consacre INT  NOT NULL,
  temps_estime   INT  NULL,
  cout_total     INT  NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX operation_users_Id_fk
  ON operation (id_user);

CREATE TABLE orderpart
(
  IdPart INT  NOT NULL,
  IdUser INT  NOT NULL,
  Qte    INT  NOT NULL,
  date   DATE NOT NULL,
  PRIMARY KEY (IdPart, IdUser, Qte, date)
)
  ENGINE = InnoDB;

CREATE INDEX fk_User_OrderPart
  ON orderpart (IdUser);

CREATE TABLE parking
(
  NumParking INT AUTO_INCREMENT
    PRIMARY KEY,
  NomParking VARCHAR(50) NULL,
  Capacity   INT         NULL
)
  ENGINE = InnoDB;

CREATE TABLE part
(
  Id            INT AUTO_INCREMENT
    PRIMARY KEY,
  Stock         INT         NULL,
  NamePart      VARCHAR(50) NULL,
  PurchasePrice FLOAT       NULL
)
  ENGINE = InnoDB;

ALTER TABLE defect
  ADD CONSTRAINT fk_part
FOREIGN KEY (partForRepair) REFERENCES part (Id);

ALTER TABLE orderpart
  ADD CONSTRAINT fk_Part_OrderPart
FOREIGN KEY (IdPart) REFERENCES part (Id);

CREATE TABLE partdefect
(
  IdPart   INT NOT NULL,
  IdDefect INT NOT NULL,
  PRIMARY KEY (IdPart, IdDefect),
  CONSTRAINT fk_Part_PartDefect
  FOREIGN KEY (IdPart) REFERENCES part (Id),
  CONSTRAINT fk_Defect_PartDefect
  FOREIGN KEY (IdDefect) REFERENCES defect (Id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Defect_PartDefect
  ON partdefect (IdDefect);

CREATE TABLE partrepairs
(
  IdPart   INT NOT NULL,
  IdRepair INT NOT NULL,
  PRIMARY KEY (IdPart, IdRepair),
  CONSTRAINT fk_Part_PartRepairs
  FOREIGN KEY (IdPart) REFERENCES part (Id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Repair_PartRepairs
  ON partrepairs (IdRepair);

CREATE TABLE place
(
  NumPlace   INT        NOT NULL
    PRIMARY KEY,
  IsOccupied TINYINT(1) NULL,
  NumPark    INT        NULL,
  CONSTRAINT fk_place
  FOREIGN KEY (NumPark) REFERENCES parking (NumParking)
)
  ENGINE = InnoDB;

CREATE INDEX fk_place
  ON place (NumPark);

CREATE TABLE preferences
(
  id           INT NOT NULL
    PRIMARY KEY,
  indifDays    INT NULL,
  vetoDays     INT NULL,
  indifTimeRep INT NULL,
  vetoTimeRep  INT NULL
)
  ENGINE = InnoDB;

CREATE TABLE prime
(
  id               INT AUTO_INCREMENT
    PRIMARY KEY,
  id_user          INT  NOT NULL,
  date_attribution DATE NOT NULL,
  montant_prime    INT  NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX prime_users_Id_fk
  ON prime (id_user);

CREATE TABLE repaircard
(
  Id             INT AUTO_INCREMENT
    PRIMARY KEY,
  IdDegree       INT          NULL,
  IdCard         INT          NULL,
  IdCar          VARCHAR(100) NULL,
  IdParkPlace    INT          NULL,
  EntryDate      DATE         NULL,
  OutDate        DATE         NULL,
  OverAllDetails TEXT         NULL,
  IdUser         INT          NULL,
  CONSTRAINT fk_CardState_RepairCard
  FOREIGN KEY (IdCard) REFERENCES cardstate (Id),
  CONSTRAINT fk_Car_RepairCard
  FOREIGN KEY (IdCar) REFERENCES car (NumPuce),
  CONSTRAINT fk_ParkPlace_RepairCard
  FOREIGN KEY (IdParkPlace) REFERENCES place (NumPlace)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Urgency_RepairCard
  ON repaircard (IdDegree);

CREATE INDEX fk_CardState_RepairCard
  ON repaircard (IdCard);

CREATE INDEX fk_Car_RepairCard
  ON repaircard (IdCar);

CREATE INDEX fk_ParkPlace_RepairCard
  ON repaircard (IdParkPlace);

CREATE INDEX fk_User_RepairCard
  ON repaircard (IdUser);

ALTER TABLE carddefect
  ADD CONSTRAINT fk_Card_CardDefect
FOREIGN KEY (IdCard) REFERENCES repaircard (Id);

ALTER TABLE cardrepairs
  ADD CONSTRAINT fk_Card_CardRepair
FOREIGN KEY (IdCard) REFERENCES repaircard (Id);

CREATE TABLE repairs
(
  Id          INT AUTO_INCREMENT
    PRIMARY KEY,
  DateRepair  DATE        NULL,
  Nature      VARCHAR(50) NULL,
  TimeSpent   FLOAT       NULL,
  Description TEXT        NULL
)
  ENGINE = InnoDB;

ALTER TABLE cardrepairs
  ADD CONSTRAINT fk_Repairs_CardRepair
FOREIGN KEY (IdRepair) REFERENCES repairs (Id);

ALTER TABLE partrepairs
  ADD CONSTRAINT fk_Repair_PartRepairs
FOREIGN KEY (IdRepair) REFERENCES repairs (Id);

CREATE TABLE salaire
(
  id                 INT AUTO_INCREMENT
    PRIMARY KEY,
  id_user            INT  NULL,
  salaire_brut       INT  NOT NULL,
  date_debut         DATE NOT NULL,
  date_fin           DATE NULL,
  temps_contrat_mois INT  NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX salaire_users_Id_fk
  ON salaire (id_user);

CREATE TABLE typecar
(
  Id   INT(10) AUTO_INCREMENT
    PRIMARY KEY,
  Type VARCHAR(50) NULL
)
  ENGINE = MyISAM;

CREATE TABLE typeuser
(
  Id     INT AUTO_INCREMENT
    PRIMARY KEY,
  Profil VARCHAR(50) NULL
)
  ENGINE = InnoDB;

CREATE TABLE urgencydegree
(
  Id          INT AUTO_INCREMENT
    PRIMARY KEY,
  Description VARCHAR(50) NULL
)
  ENGINE = InnoDB;

ALTER TABLE repaircard
  ADD CONSTRAINT fk_Urgency_RepairCard
FOREIGN KEY (IdDegree) REFERENCES urgencydegree (Id);

CREATE TABLE users
(
  Id              INT AUTO_INCREMENT
    PRIMARY KEY,
  TypeUser        INT         NULL,
  FirstName       VARCHAR(50) NULL,
  LastName        VARCHAR(50) NULL,
  DateOfBirth     DATE        NULL,
  Address         VARCHAR(50) NULL,
  Town            VARCHAR(50) NULL,
  PostalCode      INT         NULL,
  Login           VARCHAR(50) NULL,
  PasswordUser    VARCHAR(50) NULL,
  Email           VARCHAR(50) NULL,
  HiringDate      DATE        NULL,
  IncomingPerHour FLOAT       NULL,
  CONSTRAINT fk_Type
  FOREIGN KEY (TypeUser) REFERENCES typeuser (Id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_Type
  ON users (TypeUser);

ALTER TABLE operation
  ADD CONSTRAINT operation_users_Id_fk
FOREIGN KEY (id_user) REFERENCES users (Id);

ALTER TABLE orderpart
  ADD CONSTRAINT fk_User_OrderPart
FOREIGN KEY (IdUser) REFERENCES users (Id);

ALTER TABLE prime
  ADD CONSTRAINT prime_users_Id_fk
FOREIGN KEY (id_user) REFERENCES users (Id);

ALTER TABLE repaircard
  ADD CONSTRAINT fk_User_RepairCard
FOREIGN KEY (IdUser) REFERENCES users (Id);

ALTER TABLE salaire
  ADD CONSTRAINT salaire_users_Id_fk
FOREIGN KEY (id_user) REFERENCES users (Id);


