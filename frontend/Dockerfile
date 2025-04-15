FROM node:22 AS build

WORKDIR /app

COPY . .

RUN npm install
RUN npm run build

# Servir o conteúdo com Nginx
FROM nginx:alpine

# Copiar o conteúdo gerado pelo build para o diretório do Nginx
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
