FROM rabbitmq:3.8.11-management

COPY ./config/*.json /etc/rabbitmq/
COPY ./config/*.conf /etc/rabbitmq/
COPY ./plugins/*.ez /plugins/

RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
