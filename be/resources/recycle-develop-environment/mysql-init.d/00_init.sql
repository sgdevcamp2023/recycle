CREATE
    USER 'recycle-local'@'localhost' IDENTIFIED BY 'recycle-local';
CREATE
    USER 'recycle-local'@'%' IDENTIFIED BY 'recycle-local';

GRANT ALL PRIVILEGES ON *.* TO
    'recycle-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
    'recycle-local'@'%';

CREATE
    DATABASE member DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE
    DATABASE review DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE
    DATABASE notification DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
