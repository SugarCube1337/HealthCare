-- Установка схемы, если требуется
CREATE SCHEMA IF NOT EXISTS healthcare;
SET search_path TO healthcare;

-- Создание таблицы Person
CREATE TABLE Person (
                        Person_ID BIGSERIAL PRIMARY KEY,
                        Name VARCHAR(255) NOT NULL,
                        Surname VARCHAR(255) NOT NULL,
                        Gender CHAR(1),
                        Birth_Date DATE NOT NULL,
                        Contact_Info TEXT
);
CREATE SEQUENCE healthcare.person_id_seq START 1;
ALTER TABLE Person ALTER COLUMN Person_ID SET DEFAULT nextval('healthcare.person_id_seq');



-- Создание таблицы Patient
CREATE TABLE Patient (
                         Patient_ID BIGSERIAL PRIMARY KEY,
                         Person_ID BIGSERIAL NOT NULL,
                         FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID)
);

-- Создание таблицы Diagnosis
CREATE TABLE Diagnosis (
                           Diagnosis_ID BIGINT PRIMARY KEY,
                           Diagnosis_Name VARCHAR(255) NOT NULL,
                           ICD_Code VARCHAR(100) NOT NULL
);

-- Создание таблицы Diagnosis_Patient
CREATE TABLE Diagnosis_Patient (
                                   Patient_ID BIGSERIAL NOT NULL,
                                   Diagnosis_ID BIGSERIAL NOT NULL,
                                   PRIMARY KEY (Patient_ID, Diagnosis_ID),
                                   FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID),
                                   FOREIGN KEY (Diagnosis_ID) REFERENCES Diagnosis(Diagnosis_ID)
);

-- Создание таблицы Biomaterial
CREATE TABLE Biomaterial (
                             Biomaterial_ID BIGSERIAL PRIMARY KEY,
                             Patient_ID BIGSERIAL NOT NULL,
                             Collection_Date DATE NOT NULL,
                             Type VARCHAR(100) NOT NULL,
                             Delivery_Date DATE NOT NULL,
                             Status VARCHAR(50) NOT NULL,
                             Storage_Period_Hours INTEGER NOT NULL,
                             FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID)
);

-- Создание таблицы Staff_Member
CREATE TABLE Staff_Member (
                              Staff_Member_ID BIGSERIAL PRIMARY KEY,
                              Person_ID BIGSERIAL NOT NULL,
                              Research_ID BIGSERIAL NOT NULL,
                              Position VARCHAR(100) NOT NULL,
                              Qualification VARCHAR(100) NOT NULL,
                              FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID)
);

-- Создание таблицы Work_With_Biomaterials
CREATE TABLE Work_With_Biomaterials (
                                        Work_With_Biomaterial_ID BIGSERIAL PRIMARY KEY,
                                        Staff_Member_ID BIGSERIAL NOT NULL,
                                        Biomaterial_ID BIGSERIAL NOT NULL,
                                        Begin_Time DATE NOT NULL,
                                        FOREIGN KEY (Staff_Member_ID) REFERENCES Staff_Member(Staff_Member_ID),
                                        FOREIGN KEY (Biomaterial_ID) REFERENCES Biomaterial(Biomaterial_ID)
);

-- Создание таблицы Research
CREATE TABLE Research (
                          Research_ID BIGSERIAL PRIMARY KEY,
                          Work_With_Biomaterial_ID BIGSERIAL NOT NULL,
                          Research_Method VARCHAR(255) NOT NULL,
                          Technology VARCHAR(50) CHECK (Technology IN ('value1', 'value2', 'value3')),
                          Research_Method_ID BIGSERIAL,
                          Date DATE NOT NULL,
                          FOREIGN KEY (Work_With_Biomaterial_ID) REFERENCES Work_With_Biomaterials(Work_With_Biomaterial_ID)
);

-- Создание таблицы Medical_Report
CREATE TABLE Medical_Report (
                                Medical_Report_ID BIGSERIAL PRIMARY KEY,
                                Staff_Member_ID BIGSERIAL NOT NULL,
                                Research_ID BIGSERIAL  NOT NULL,
                                Result VARCHAR(255),
                                FOREIGN KEY (Staff_Member_ID) REFERENCES Staff_Member(Staff_Member_ID),
                                FOREIGN KEY (Research_ID) REFERENCES Research(Research_ID)
);

-- Создание таблицы Research_Registration
CREATE TABLE Research_Registration (
                                       Research_Registration_ID BIGSERIAL  PRIMARY KEY,
                                       Research_ID BIGSERIAL NOT NULL,
                                       Patient_ID BIGSERIAL NOT NULL,
                                       Date DATE NOT NULL,
                                       Time_Start TIME NOT NULL,
                                       Time_End TIME NOT NULL,
                                       FOREIGN KEY (Research_ID) REFERENCES Research(Research_ID),
                                       FOREIGN KEY (Patient_ID) REFERENCES Patient(Patient_ID)
);

-- Создание таблицы Candidate
CREATE TABLE Candidate (
                           Candidate_ID BIGSERIAL PRIMARY KEY,
                           Want_Position VARCHAR(255) NOT NULL,
                           Filling_Date DATE NOT NULL,
                           Gender CHAR(1),
                           Qualification VARCHAR(100) NOT NULL,
                           Experience INTEGER NOT NULL
);

-- Создание таблицы Vacancy
CREATE TABLE Vacancy (
                         Vacancy_ID BIGSERIAL PRIMARY KEY,
                         Position VARCHAR(255) NOT NULL,
                         Requirements TEXT NOT NULL,
                         Opening_Date DATE NOT NULL,
                         Min_Salary INTEGER NOT NULL,
                         Status VARCHAR(50),
                         Type_Of_Employment VARCHAR(50)
);

-- Создание таблицы Candidate_Vacancy
CREATE TABLE Candidate_Vacancy (
                                   Candidate_ID BIGSERIAL NOT NULL,
                                   Vacancy_ID BIGSERIAL NOT NULL,
                                   PRIMARY KEY (Candidate_ID, Vacancy_ID),
                                   FOREIGN KEY (Candidate_ID) REFERENCES Candidate(Candidate_ID),
                                   FOREIGN KEY (Vacancy_ID) REFERENCES Vacancy(Vacancy_ID)
);
