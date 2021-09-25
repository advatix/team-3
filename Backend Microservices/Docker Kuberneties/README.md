https://www.youtube.com/watch?v=X48VuDVv0do
https://dzone.com/articles/how-to-set-up-jenkins-on-kubernetes
https://phoenixnap.com/kb/install-kubernetes-on-ubuntu
[How To Install Kubernetes Cluster On Ubuntu](https://www.edureka.co/blog/install-kubernetes-on-ubuntu)
https://medium.com/@vishal.sharma./installing-configuring-kubernetes-cluster-on-ubuntu-18-04-lts-hosts-f37b959c8410
[Kubernetes Dashboard](https://techexpert.tips/kubernetes/kubernetes-dashboard-installation-ubuntu/)

1. Install Docker
2. Install Kubernetes
3. Install Minikube
4. Install Helm
5. Kube Proxy (Optional)
5. Commands:
	```minikube start```
	```kubectl```
	```kubectl get nodes```
	```minukube status```
	```kubectl varsion```
	```kubectl get pod```
	```kubectl get pod -o wide```
	```kubectl get replicaset```
	```kubectl get services```
	```kubectl get namespaces```
	```kubectl create -h```

	```kubectl create namespace my-namespace```

	```kubectl create deployment NAME --image=image [--dry-run] [options] (kubectl create deployment nginx-depl --image=nginx)```
	```kubectl edit deployment NAME```
	```kubectl get deployment```
	```kubectl get deployment -o yaml```
	```kubectl delete deployment DEPLOYMENTNAME```
	
	```kubectl logs PODNAME```
	```kubectl describe pod PODNAME```
	```kubectl exec -it PODNAME -- /bin/bash```

	```kubectl apply -f nginx-deployment.yaml```
	```kubectl delete -f nginx-deployment.yaml```

	```kubectl get nodes | pod | services | replicaset | deployment | all [--watch]```

6. Create nginx-deployment.yaml File
7. Create nginx-service.yaml File
8. ```kubectl apply -f nginx-deployment.yaml``` ```kubectl apply -f nginx-service.yaml```

9. Create NameSpace ```kubectl create namespace my-namespace database-namespace```
10. Create mongodb-deployment.yaml File
11. Create mongodb-service.yaml File
12. Create mongodb-secret.yaml File > Set Username and Password in Base64 Encoded
	```echo -n 'username' | base64``` results *dXNlcm5hbWU=*
	```echo -n 'password' | base64``` results *cGFzc3dvcmQ=*
13. Add mongodeb-service configurations in mongodb-deployment.yaml by seperating three dashes
14. Copy IP Address of Pod EndPoints. ```kubectl describe service mongo-service``` ```kubectl get all -o wide | grep mongodb```
15. Create mongodb-config.yaml File
16. Create mongo-express-deployment.yaml File
	> If the type *Loadbalancer* is enabled then the service can be used _Externaly_ and provide the external ip with command ```minikube service mongo-express-service```.
	> Node port that used for extrenal can be in range of *30000 - 32767*.


17. Install Ingress Controller in Minikube
18. Create IngressDashboard.yaml
	```minikube addons enable ingress```
	```kubectl apply -f dashboard-ingress.yaml```
	```kubectl get ingress -n kubernetes-dashboard```
	```kubectl get ingress -n kubernetes-dashboard --watch```
	```kubectl describe ingress dashboard-ingress -n kubernetes-dashboard```

19. Create Hostname in /etc/hosts
20. Configure a custom backend (Optional)
