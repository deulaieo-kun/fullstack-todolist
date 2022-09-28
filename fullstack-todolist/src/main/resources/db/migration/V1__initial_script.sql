DROP SCHEMA IF EXISTS adrianne cascade;
CREATE SCHEMA adrianne;

CREATE TABLE adrianne.todolist (
    todo_id uuid,
    todo varchar(300),
    created_date TIMESTAMP WITH TIME ZONE,
    modified_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (todo_id)
);