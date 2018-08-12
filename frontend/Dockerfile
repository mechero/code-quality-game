FROM node:8
COPY ./ /usr/src/code-quality-game-frontend/
WORKDIR /usr/src/code-quality-game-frontend
RUN npm install && npm build
EXPOSE 3000
CMD ["npm", "start"]
