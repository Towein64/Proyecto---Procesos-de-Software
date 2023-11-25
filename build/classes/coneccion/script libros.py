import sqlite3

# Conectar a la base de datos (creará el archivo bd_1.db si no existe)
conn = sqlite3.connect('bd_1.db')
cursor = conn.cursor()

# Crear la tabla libros si no existe
cursor.execute('''
    CREATE TABLE IF NOT EXISTS libros (
        id INTEGER PRIMARY KEY,
        nombre TEXT,
        autor TEXT,
        precio REAL,
        stock INTEGER
    )
''')

# Datos de ejemplo para libros
libros_data = [
    ('Harry Potter', 'J.K. Rowling', 19.99),
    ('1984', 'George Orwell', 15.99),
    ('Cien años de soledad', 'Gabriel García Márquez', 24.99),
    ('El Señor de los Anillos', 'J.R.R. Tolkien', 29.99),
    ('Crimen y castigo', 'Fyodor Dostoevsky', 18.99),
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 22.99),
    ('Orgullo y prejuicio', 'Jane Austen', 17.99),
    ('To Kill a Mockingbird', 'Harper Lee', 16.99),
    ('The Great Gatsby', 'F. Scott Fitzgerald', 20.99),
    ('One Hundred Years of Solitude', 'Gabriel García Márquez', 24.99),
]

# Insertar datos en la tabla y manejar stock
for libro in libros_data:
    cursor.execute('''
        INSERT INTO libros (nombre, autor, precio, stock)
        VALUES (?, ?, ?, 0)  -- El stock inicial se establece en 0
    ''', libro)

# Aumentar el stock para libros repetidos
for i in range(5):  # Agregar 5 copias de libros existentes
    for libro in libros_data:
        cursor.execute('''
            UPDATE libros
            SET stock = stock + 1
            WHERE nombre = ? AND autor = ?
        ''', (libro[0], libro[1]))

# Guardar los cambios y cerrar la conexión
conn.commit()
conn.close()

# Agregar mensajes de impresión para verificar
print("Base de datos y tabla creadas exitosamente.")
print("Datos insertados en la tabla libros.")
print("Stock actualizado para libros repetidos.")
