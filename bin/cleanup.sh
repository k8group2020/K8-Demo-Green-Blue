kubectl delete deployments gke-microapp-canary-deploy-blue gke-microapp-canary-deploy-green
kubectl delete services gke-microapp-service
kubectl delete gateway gke-microapp-pod-gateway
kubectl delete destinationrule gke-microapp-canary-rule
kubectl delete virtualservice gke-microapp-pod-virtualservice-canary
kubectl delete virtualservice gke-microapp-pod-service-canary