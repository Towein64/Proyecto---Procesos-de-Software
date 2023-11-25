import sqlite3

# Conectar a la base de datos (creará el archivo bd_1.db si no existe)
conn = sqlite3.connect('bd_1.db')
cursor = conn.cursor()

# Crear la tabla clientes si no existe
cursor.execute('''
    CREATE TABLE IF NOT EXISTS clientes (
        id INTEGER PRIMARY KEY,
        nombre TEXT,
        apellido TEXT,
        correo TEXT
    )
''')

# Datos de ejemplo para clientes
clientes_data = [
    ('1', 'Juan', 'Pérez', 'juan@example.com'),
    ('2', 'María', 'Gómez', 'maria@example.com'),
    ('3', 'Carlos', 'López', 'carlos@example.com'),
    ('4', 'Ana', 'Martínez', 'ana@example.com'),
    ('5', 'Miguel', 'Rodríguez', 'miguel@example.com'),
    ('6', 'Laura', 'Hernández', 'laura@example.com'),
    ('7', 'Pedro', 'Díaz', 'pedro@example.com'),
    ('8', 'Sofía', 'García', 'sofia@example.com'),
    ('9', 'Javier', 'Ruiz', 'javier@example.com'),
    ('10', 'Isabel', 'Fernández', 'isabel@example.com'),
]

# Insertar datos en la tabla clientes
for cliente in clientes_data:
    cursor.execute('''
        INSERT INTO clientes (id, nombre, apellido, correo)
        VALUES (?, ?, ?, ?)
    ''', cliente)

# Verificar la inserción de datos
cursor.execute('SELECT * FROM clientes')
clientes_registros = cursor.fetchall()

# Imprimir los resultados
print("Registros en la tabla clientes:")
for cliente in clientes_registros:
    print(cliente)

# Guardar los cambios y cerrar la conexión
conn.commit()
conn.close()

# Agregar mensajes de impresión para verificar
print("Base de datos y tabla creadas exitosamente.")
print("Datos insertados en la tabla clientes.")
print("Registros de clientes mostrados para verificación.")
