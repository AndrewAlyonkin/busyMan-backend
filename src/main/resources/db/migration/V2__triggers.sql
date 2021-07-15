DROP TRIGGER IF EXISTS task_AFTER_INSERT;
DROP TRIGGER IF EXISTS task_AFTER_DELETE;
DROP TRIGGER IF EXISTS task_AFTER_UPDATE;

-- вставка новой задачи в таблицу task
DELIMITER $$
CREATE TRIGGER task_AFTER_INSERT
    AFTER INSERT
    ON task
    FOR EACH ROW
BEGIN
    -- если вставляем новую таску с категорией, и она завершена, увеличиваем счетчик завершенных задач для категории
    if (ifnull(NEW.category_id, 0) > 0 AND ifnull(NEW.completed, 0) = 1) then
        update category
        set completed_count = (ifnull(completed_count, 0) + 1)
        where id = NEW.category_id
          and user_id = NEW.user_id;
    end if;
    -- если вставляем новую таску с категорией, и она не завершена, увеличиваем счетчик незавершенных задач для категории
    if (ifnull(NEW.category_id, 0) > 0 AND ifnull(NEW.completed, 0) = 0) then
        update category c
        set uncompleted_count = (ifnull(uncompleted_count, 0) + 1)
        where id = NEW.category_id
          and user_id = NEW.user_id;
    end if;

    -- подсчет общей статистики по задачам
    if ifnull(NEW.completed, 0) = 1 then
        -- ...если завершена, то увеличиваем общий счетчик завершенных задач
        update users set completed_total = (ifnull(completed_total, 0) + 1) where id = NEW.user_id;
    else
        -- ...а если не завершена - увеличиваем общий счетчик незавершенных задач
        update users set uncompleted_total = (ifnull(uncompleted_total, 0) + 1) where id = NEW.user_id;
    end if;
END $$


-- удаление задачи из таблицы
DELIMITER $$
CREATE TRIGGER task_AFTER_DELETE
    AFTER DELETE
    ON task
    FOR EACH ROW
BEGIN
    -- если задача имеет категорию  и она завершена, уменьшаем счетчик завершенных задач для категории
    if (ifnull(OLD.category_id, 0) > 0 AND ifnull(OLD.completed, 0) = 1) then
        update category
        set completed_count = (ifnull(completed_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
    end if;
    -- если задача имеет категорию, и она не завершена, уменьшаем счетчик невыполненных задач для категории
    if (ifnull(OLD.category_id, 0) > 0 AND ifnull(OLD.completed, 0) = 0) then
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
    end if;

    -- подсчет общей статистики по категориям
    if ifnull(OLD.completed, 0) = 1 then
        -- если завершена, то уменьшаем общий счетчик завершенных задач
        update users set completed_total = (ifnull(completed_total, 0) - 1) where id = OLD.user_id;
    else
        -- если не завершена, то уменьшаем общий счетчик незавершенных задач
        update users set uncompleted_total = (ifnull(uncompleted_total, 0) - 1) where id = OLD.user_id;
    end if;
END $$


-- обновление задачи
DELIMITER $$
CREATE TRIGGER task_AFTER_UPDATE
    AFTER UPDATE
    ON task
    FOR EACH ROW
BEGIN
    -- статус задачи стал - завершена и задача осталась в той же категории
    IF (ifnull(OLD.completed, 0) <> ifnull(NEW.completed, 0) AND NEW.completed = 1 AND
        ifnull(OLD.category_id, 0) = ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик незавершенных задач на 1, увеличиваем счетчик завершенных задач на 1
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) - 1),
            completed_count   = (ifnull(completed_count, 0) + 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- уменьшаем общий счетчик незавершенных задач на 1, увеличиваем общий счетчик завершенных задач на 1
        update users
        set uncompleted_total = (ifnull(uncompleted_total, 0) - 1),
            completed_total   = (ifnull(completed_total, 0) + 1)
        where id = OLD.user_id;
    END IF;

    -- статус задачи стал - не завершена и задача осталась в той же категории
    IF (ifnull(OLD.completed, 0) <> ifnull(NEW.completed, 0) AND NEW.completed = 0 AND
        ifnull(OLD.category_id, 0) = ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик завершенных задач на 1, увеличиваем счетчик незавершенных задач на 1
        update category
        set completed_count   = (ifnull(completed_count, 0) - 1),
            uncompleted_count = (ifnull(uncompleted_count, 0) + 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- уменьшаем общий счетчик завершенных задач на 1, увеличиваем общий счетчик незавершенных задач на 1
        update users
        set completed_total   = (ifnull(completed_total, 0) - 1),
            uncompleted_total = (ifnull(uncompleted_total, 0) + 1)
        where id = OLD.user_id;
    END IF;

    -- изменили категорию выполненной задачи
    IF (ifnull(OLD.completed, 0) = ifnull(NEW.completed, 0) AND NEW.completed = 1 AND
        ifnull(OLD.category_id, 0) <> ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик завершенных задач в исходной категории
        update category
        set completed_count = (ifnull(completed_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- увеличиваем счетчик завершенных задач в новой категории
        update category
        set completed_count = (ifnull(completed_count, 0) + 1)
        where id = NEW.category_id
          and user_id = OLD.user_id;
    END IF;

    -- изменили категорию невыполненной задачи
    IF (ifnull(OLD.completed, 0) = ifnull(NEW.completed, 0) AND NEW.completed = 0 AND
        ifnull(OLD.category_id, 0) <> ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик незавершенных задач в исходной категории
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- увеличиваем счетчик незавершенных задач в новой категории
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) + 1)
        where id = NEW.category_id
          and user_id = OLD.user_id;
    END IF;

    -- изменили категорию и статус задачи с выполненной на невыполненную
    IF (ifnull(OLD.completed, 0) <> ifnull(NEW.completed, 0) AND NEW.completed = 0 AND
        ifnull(OLD.category_id, 0) <> ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик завершенных задач в исходной категории
        update category
        set completed_count = (ifnull(completed_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- увеличиваем счетчик незавершенных задач в новой категории
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) + 1)
        where id = NEW.category_id
          and user_id = OLD.user_id;
        -- уменьшаем общий счетчик завершенных задач на 1, увеличиваем общий счетчик незавершенных задач на 1
        update users
        set uncompleted_total = (ifnull(uncompleted_total, 0) + 1),
            completed_total   = (ifnull(completed_total, 0) - 1)
        where id = OLD.user_id;
    END IF;

    -- изменили категорию и статус задачи с невыполненной на выполненную
    IF (ifnull(OLD.completed, 0) <> ifnull(NEW.completed, 0) AND NEW.completed = 1 AND
        ifnull(OLD.category_id, 0) <> ifnull(NEW.category_id, 0)) THEN
        -- уменьшаем счетчик незавершенных задач в исходной категории
        update category
        set uncompleted_count = (ifnull(uncompleted_count, 0) - 1)
        where id = OLD.category_id
          and user_id = OLD.user_id;
        -- увеличиваем счетчик завершенных задач в новой категории
        update category
        set completed_count = (ifnull(completed_count, 0) + 1)
        where id = NEW.category_id
          and user_id = OLD.user_id;
        -- уменьшаем общий счетчик незавершенных задач на 1, увеличиваем общий счетчик завершенных задач на 1
        update users
        set uncompleted_total = (ifnull(uncompleted_total, 0) - 1),
            completed_total   = (ifnull(completed_total, 0) + 1)
        where id = OLD.user_id;
    END IF;
END $$

