FROM python:alpine3.20

WORKDIR /
COPY main.py main.py
COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

EXPOSE 9090/tcp

ENTRYPOINT python main.py