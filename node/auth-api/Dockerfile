FROM node:14-alpine
WORKDIR .
COPY package.json ./
RUN yarn
COPY . .
EXPOSE 8080
CMD ["node", "app.js"]