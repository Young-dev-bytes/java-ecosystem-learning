FROM nvidia/cuda:12.5.0-devel-ubuntu22.04 

# ENV http_proxy http://proxysys.his.hihonor.com:8080
# ENV https_proxy http://proxysys.his.hihonor.com:8080
ENV CUDA_HOME=/usr/local/cuda

RUN mkdir -p /opt/miniconda3/
RUN apt-get update && apt-get install wget -y && wget https://mirrors.tuna.tsinghua.edu.cn/anaconda/miniconda/Miniconda3-py310_23.3.1-0-Linux-x86_64.sh -O /opt/miniconda3/miniconda.sh
RUN bash /opt/miniconda3/miniconda.sh -b -u -p /opt/miniconda3
RUN /opt/miniconda3/bin/conda init bash
RUN /opt/miniconda3/bin/conda init zsh
RUN rm -rf /opt/miniconda3/miniconda.sh

ENV PATH /opt/miniconda3/bin:$PATH

RUN apt-get update

RUN apt-get install git -y

RUN pip install gradio
RUN pip install transformers==4.41.1 -i https://mirrors.aliyun.com/pypi/simple/

RUN apt install -y infiniband-diags perftest ibverbs-providers libibumad3 libibverbs1 libnl-3-200 libnl-route-3-200 librdmacm1

ENV LD_LIBRARY_PATH /usr/local/cuda/extras/CUPTI/lib64:/usr/local/cuda/lib64:$LD_LIBRARY_PATH




RUN echo 'Young, You are the best!'

COPY ./code-server-4.89.0-linux-amd64 /young/code-server

RUN pwd

RUN ls -l /young

COPY ./999-start.sh /young/

WORKDIR /young







