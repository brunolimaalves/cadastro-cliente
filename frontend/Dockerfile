# Etapa de build
FROM node:18-alpine AS builder
WORKDIR /app
# Copia os arquivos de configuração e instala as dependências
COPY package.json package-lock.json ./
RUN npm install
# Copia o restante dos arquivos e realiza o build
COPY . .
RUN npm run build

# Etapa de produção utilizando o Nginx para servir os arquivos estáticos
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
