microk8s.kubectl delete -f ./k8s/service-account.yaml
microk8s.kubectl delete -f ./k8s/webapp-ds.yaml
microk8s.kubectl delete -f ./k8s/webapp-gateway.yaml
microk8s.kubectl delete -f ./k8s/webapp-vs-all.yaml
microk8s.kubectl delete -f ./k8s/webapp.yaml