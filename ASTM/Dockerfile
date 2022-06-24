FROM python:3.10-alpine

RUN apk update
RUN apk --no-cache add curl
RUN apk --no-cache add git
    
RUN git clone https://github.com/senaite/senaite.astm.git
WORKDIR "/senaite.astm"
RUN pip install -e .

RUN mkdir -p /astm/output/

EXPOSE 3113
CMD [ "senaite-astm-server", "-v", "-o", "/astm/output/", "-p", "3113"]
    
    