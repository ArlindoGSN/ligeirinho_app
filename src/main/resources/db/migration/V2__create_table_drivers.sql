CREATE TABLE drivers (
                         id SERIAL PRIMARY KEY,
                         company_id UUID NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         cpf VARCHAR(11) NOT NULL,
                         cnh VARCHAR(9) NOT NULL,
                         email VARCHAR(255),
                         phone VARCHAR(20),
                         address VARCHAR(255),
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP,
                         status BOOLEAN NOT NULL,
                         CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);
