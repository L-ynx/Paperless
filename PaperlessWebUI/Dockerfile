FROM nginx
COPY PaperlessWebUI /usr/share/nginx/html
COPY PaperlessWebUI/cors.conf /etc/nginx/conf.d/cors.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
