CREATE OR REPLACE FUNCTION add_patient(
    p_name VARCHAR,
    p_surname VARCHAR,
    p_gender CHAR(1),
    p_birth_date DATE,
    p_contact_info TEXT,
    p_diagnosis VARCHAR
) RETURNS BIGINT AS $$
DECLARE
    new_person_id BIGINT;
    new_patient_id BIGINT;
    v_diagnosis_id BIGINT;  -- Переменная для хранения ID диагноза
BEGIN
    -- Добавляем нового человека
    INSERT INTO Person (Name, Surname, Gender, Birth_Date, Contact_Info)
    VALUES (p_name, p_surname, p_gender, p_birth_date, p_contact_info)
    RETURNING id INTO new_person_id;

    -- Добавляем нового пациента
    INSERT INTO Patient (Person_ID)
    VALUES (new_person_id)
    RETURNING Patient_ID INTO new_patient_id;

    -- Получаем ID диагноза по имени диагноза
    SELECT Diagnosis_ID INTO v_diagnosis_id
    FROM Diagnosis
    WHERE Diagnosis_Name = p_diagnosis
    LIMIT 1;

    -- Если диагноз не найден, выбрасываем исключение
    IF v_diagnosis_id IS NULL THEN
        RAISE EXCEPTION 'Diagnosis with name % does not exist', p_diagnosis;
    END IF;

    -- Связываем пациента с диагнозом
    INSERT INTO Diagnosis_Patient (Patient_ID, Diagnosis_ID)
    VALUES (new_patient_id, v_diagnosis_id);

    -- Возвращаем ID нового пациента
    RETURN new_patient_id;
END;
$$ LANGUAGE plpgsql;
INSERT INTO Diagnosis (Diagnosis_Name, ICD_Code)

VALUES ('ill1337', 'ICD0001');
CREATE SEQUENCE diagnosis_id_seq START 1;

ALTER TABLE Diagnosis
    ALTER COLUMN Diagnosis_ID SET DEFAULT nextval('diagnosis_id_seq');




SELECT add_patient(
               'Biba',
               'Brown',
               'M',
               '1985-04-23',
               'biba.brown@example.com',
               'ill1337'
           );
