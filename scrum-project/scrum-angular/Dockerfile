# Stage 1: Compile and Build angular codebase

# Use official node image as the base image
FROM node:latest as build

# Set the working directory
WORKDIR /usr/local/app
# Add the source code to app

COPY ./scrum-project/scrum-angular/ /usr/local/app/
#primeiro parametro é o diretorio do docker, segundo é o diretorio do container

# keep the container running
CMD tail -f /dev/null

# Install all the dependencies
RUN npm install --legacy-peer-dep
# # Generate the build of the application
RUN npm run build


# Stage 2: Serve app with nginx server
# Use official nginx image as the base image
FROM nginx:latest
# # Copy the build output to replace the default nginx contents.
COPY --from=build /usr/local/app/dist/scrum-angular /usr/share/nginx/html
# # Expose port 80
EXPOSE 4200