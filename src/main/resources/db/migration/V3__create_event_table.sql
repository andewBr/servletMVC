CREATE TABLE Event (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       user_id INT,
                       file_id INT,
                       FOREIGN KEY (user_id) REFERENCES User(id),
                       FOREIGN KEY (file_id) REFERENCES File(id)
);
