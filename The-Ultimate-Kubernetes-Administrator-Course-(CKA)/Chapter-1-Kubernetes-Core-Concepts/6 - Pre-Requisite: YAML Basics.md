syntax pf yaml

k-v , object, list, list of object

```yaml
microservice:
  app: user
  port: 9000
  version: 1.7
  deployed: off
  list:
    - li1
    - li2
  list2: [li1, li2]
```

```yaml
# list of object
microservice:
  - app: user1
    port: 9000
    version: 1.7
    deployed: off
  - app: user2
    port: 9000
    version: 1.7
    deployed: off
```

```yaml
# pod configuration

apiVersion: v1
kind: Pod
metadata:
  name: nginx
  labels:
    app: nginx
spec:
  containers:
    - name: nginx-container
      image: nginx
      ports:
        - containerPort: 80
      volumeMounts:
        - name: nginx-vol
          mountPath: /usr/nginx/html
    - name: sidecar-container
      image: some-image
      command: ["/bin/sh"]
      args: ["-c", "echi hello"]
```

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mos-config-file
data:
  mos.conf: | # multible line
    log_dest stdout
    log_type all
    log_timestamp true
    listener 9001

--- # multible service

apiVersion: v1
kind: Secret
```

```yaml
# readiness probe
command:
  - /bin/sh
  - -ec
  - >-
    mysql -h 127.0.0.1 -u root -p$MYSQL_ROOT_PASSWORD -e 'SELECT 1'
```
