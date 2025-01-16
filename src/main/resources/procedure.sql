-- Создание процедуры для закрытия вакансии
CREATE OR REPLACE PROCEDURE close_vacancy(
    p_vacancy_id BIGINT
)
    LANGUAGE plpgsql AS $$
DECLARE
vacancy_exists BOOLEAN;
BEGIN
    -- Проверяем, существует ли вакансия с данным ID
SELECT EXISTS (
    SELECT 1
    FROM Vacancy
    WHERE id = p_vacancy_id
) INTO vacancy_exists;

-- Если вакансия не найдена, выбрасываем исключение
IF NOT vacancy_exists THEN
        RAISE EXCEPTION 'Vacancy with ID % does not exist', p_vacancy_id;
END IF;

    -- Закрытие вакансии
UPDATE Vacancy
SET Status = 'Closed'
WHERE id = p_vacancy_id;

-- Логирование успешного выполнения
RAISE NOTICE 'Vacancy with ID % successfully closed', p_vacancy_id;
END;
$$;
CALL close_vacancy(1);
