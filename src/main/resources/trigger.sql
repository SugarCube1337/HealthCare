-- Добавление поля для даты окончания хранения
ALTER TABLE Biomaterial
    ADD COLUMN Storage_Expiry_Date DATE;

-- Создание триггера для расчета даты окончания хранения
CREATE OR REPLACE FUNCTION set_biomaterial_expiry_date()
RETURNS TRIGGER AS $$
BEGIN
    -- Обновление даты окончания хранения на основе COLLECTION_DATE и STORAGE_PERIOD
    NEW.Storage_Expiry_Date := NEW.Collection_Date + INTERVAL '1 hour' * NEW.Storage_Period_Hours;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Создание триггера, который будет срабатывать после вставки в таблицу Biomaterial
CREATE TRIGGER biomaterial_expiry_date_trigger
    AFTER INSERT ON Biomaterial
    FOR EACH ROW
    EXECUTE FUNCTION set_biomaterial_expiry_date();


-- Вставим новый биоматериал, чтобы проверить триггер
INSERT INTO Biomaterial (Patient_ID, Collection_Date, Type, Delivery_Date, Status, Storage_Period_Hours)
VALUES (23, '2025-01-16', 'Blood', '2025-01-16', 'Pending', 48);

-- Проверим результат
SELECT * FROM Biomaterial WHERE Patient_ID = 1;
