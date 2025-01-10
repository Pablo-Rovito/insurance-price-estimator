# Usamos la imagen oficial de PostgreSQL
FROM postgres:13

# Copiar el archivo de inicialización a la carpeta de configuración de PostgreSQL
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto por defecto de PostgreSQL
EXPOSE 5432