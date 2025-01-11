# Etapa de construcción
FROM node:18 AS build

WORKDIR /app

# Copiar los archivos package.json y package-lock.json
COPY package.json package-lock.json ./

# Instalar las dependencias
RUN npm install

# Copiar todo el código fuente
COPY . .

# Construir la aplicación
RUN npm run build

# Etapa de producción
FROM nginx:alpine

# Copiar los archivos construidos al contenedor de Nginx
COPY --from=build /app/build /usr/share/nginx/html
COPY ./default.conf /etc/nginx/conf.d/default.conf

# Exponer el puerto donde Nginx servirá el frontend
EXPOSE 3000

# Ejecutar Nginx
CMD ["nginx", "-g", "daemon off;"]