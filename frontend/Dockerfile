FROM node:18-alpine as builder
WORKDIR /frontend
COPY package*.json .
RUN npm install
# copy rest of files
COPY . .
RUN npm run build
# Run stage
FROM nginx:1.19.0
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /frontend/dist .
ENTRYPOINT ["nginx", "-g", "daemon off;"]