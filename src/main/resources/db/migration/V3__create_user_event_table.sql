CREATE TABLE UserEvent (
                           user_id INT,
                           event_id INT,
                           PRIMARY KEY (user_id, event_id),
                           FOREIGN KEY (user_id) REFERENCES User(id),
                           FOREIGN KEY (event_id) REFERENCES Event(id)
);