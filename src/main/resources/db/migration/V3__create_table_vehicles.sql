-- Criação da tabela vehicles
CREATE TABLE IF NOT EXISTS vehicles (
                          id SERIAL PRIMARY KEY,                      -- Chave primária com auto-incremento
                          driver_id INTEGER NOT NULL,                 -- Chave estrangeira para a tabela drivers
                          plate VARCHAR(255) NOT NULL,                -- Placa do veículo
                          model VARCHAR(255) NOT NULL,                -- Modelo do veículo
                          color VARCHAR(50),                          -- Cor do veículo
                          capacity INTEGER NOT NULL,                  -- Capacidade do veículo
                          status BOOLEAN NOT NULL,                    -- Status do veículo (ativo/inativo)


                          CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES drivers (id) ON DELETE CASCADE
);

-- Índices opcionais
CREATE INDEX idx_vehicle_driver_id ON vehicles(driver_id); -- Índice para otimizar consultas por driver_id
CREATE INDEX idx_vehicle_plate ON vehicles(plate);         -- Índice para consultas por placa
