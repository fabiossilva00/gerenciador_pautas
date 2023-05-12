FROM postgres:11.19-bullseye

LABEL description="Postgres Image 11"

COPY scripts/*.sql /docker-entrypoint-initdb.d/