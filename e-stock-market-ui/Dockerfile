# Stage 1
FROM node:10-alpine as build-step
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY package.json /usr/src/app
COPY /dist/
RUN npm install
COPY . /usr/src/app
RUN npm run build --prod