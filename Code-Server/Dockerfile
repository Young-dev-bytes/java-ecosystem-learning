# # This will start a code-server container and expose it at http://127.0.0.1:8080.
# # It will also mount your current directory into the container as `/home/coder/project`
# # and forward your UID/GID so that all file system operations occur as your user outside
# # the container.
# #
# # Your $HOME/.config is mounted at $HOME/.config within the container to ensure you can
# # easily access/modify your code-server config in $HOME/.config/code-server/config.json
# # outside the container.
# mkdir -p ~/.config
# docker run -it --name code-server -p 127.0.0.1:8080:8080 \
#   -v "$HOME/.local:/home/coder/.local" \
#   -v "$HOME/.config:/home/coder/.config" \
#   -v "$PWD:/home/coder/project" \
#   -u "$(id -u):$(id -g)" \
#   -e "DOCKER_USER=$USER" \
#   codercom/code-server:latest

FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    curl \
    sudo \
    git \
    dumb-init \
    tar \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -m coder \
    && echo 'coder ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers.d/nopasswd \
    && chmod 0440 /etc/sudoers.d/nopasswd

USER coder
WORKDIR /home/coder

RUN curl -fsSL -o code-server.tar.gz https://github.com/coder/code-server/releases/download/v4.89.1/code-server-4.89.1-linux-arm64.tar.gz \
    && tar -xzf code-server.tar.gz --strip-components=1 \
    && rm code-server.tar.gz

EXPOSE 8088

ENV PASSWORD="123456"

CMD ["dumb-init", "bin/code-server", "--bind-addr", "0.0.0.0:8080", "."]

