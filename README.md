# Overview

* System provides support to add tenants
* A tenant can add subscription
* Each subscription requires the topic for which feedback is needed, the source to collect feedback and other required data to collect feedback
* A tenant can push feedback corresponding to a subscription (push integration model)
* System runs a cron job (daily at 6 AM) to pull feedback for all subscriptions (pull integration model)
* System currently has three sources - [ DISCOURSE, PLAY_STORE, TWITTER ]

# Postman Collection for the Service: 

```json
{
	"info": {
		"_postman_id": "baca0892-3cda-43de-bdad-144c3757669d",
		"name": "Feedback Ingestion Service",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "16251916"
	},
	"item": [
		{
			"name": "Tenant",
			"item": [
				{
					"name": "addTenant",
					"request": {
						"method": "POST",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/tenant/add?name=SampleTenant&userName=sampleTenant",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"tenant",
								"add"
							],
							"query": [
								{
									"key": "name",
									"value": "SampleTenant"
								},
								{
									"key": "userName",
									"value": "sampleTenant"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllTenant",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/tenant/getAll",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"tenant",
								"getAll"
							]
						}
					},
					"response": []
				},
				{
					"name": "getTenantByUserName",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/tenant/getByUsername?userName=sampleTenant",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"tenant",
								"getByUsername"
							],
							"query": [
								{
									"key": "userName",
									"value": "sampleTenant"
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Subscription",
			"item": [
				{
					"name": "addSubscriptionPlayStore",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"tenantUserName\": \"sampleTenant\",\n    \"topic\": \"My App\",\n    \"source\": \"PLAY_STORE\",\n    \"requiredData\": {\n        \"playStoreAppId\": \"xyz\"\n    }\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/subscription/add",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"add"
							]
						}
					},
					"response": []
				},
				{
					"name": "addSubscriptionDiscourse",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"tenantUserName\": \"sampleTenant\",\n    \"topic\": \"My App\",\n    \"source\": \"DISCOURSE\",\n    \"requiredData\": {\n        \"searchString\": \"abc\"\n    }\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/subscription/add",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"add"
							]
						}
					},
					"response": []
				},
				{
					"name": "addSubscriptionTwitter",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"tenantUserName\": \"sampleTenant\",\n    \"topic\": \"My App\",\n    \"source\": \"TWITTER\",\n    \"requiredData\": {\n        \"searchString\": \"www\"\n    }\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/subscription/add",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"add"
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllSubscriptionsForATenant",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/subscription/getAllForTenant?tenantUserName=sampleTenant",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"getAllForTenant"
							],
							"query": [
								{
									"key": "tenantUserName",
									"value": "sampleTenant"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllSubscriptions",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/subscription/getAll",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"getAll"
							]
						}
					},
					"response": []
				},
				{
					"name": "removeSubscription",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"tenantUserName\": \"sampleTenant\",\n    \"topic\": \"My App\",\n    \"source\": \"DISCOURSE\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/subscription/remove",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"subscription",
								"remove"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Feedback",
			"item": [
				{
					"name": "addFeedbackDiscourse",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 2,\n    \"name\": \"abc\",\n    \"username\": \"abc\",\n    \"avatar_template\": \"abc\",\n    \"created_at\": \"2015-07-05T22:02:57.980Z\",\n    \"like_count\": 5,\n    \"blurb\": \"feedback blurb\",\n    \"post_number\": 2,\n    \"topic_title_headline\": \"abc\",\n    \"topic_id\": \"abc\"\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/feedback/add?tenantUserName=sampleTenant&topic=My App&source=DISCOURSE",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"add"
							],
							"query": [
								{
									"key": "tenantUserName",
									"value": "sampleTenant"
								},
								{
									"key": "topic",
									"value": "My App"
								},
								{
									"key": "source",
									"value": "DISCOURSE"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "addFeedbackTwitter",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 2,\n    \"username\": \"abc\",\n    \"created_at\": \"2015-07-05T22:02:57.980Z\",\n    \"tweet\": \"hi\",\n    \"likeCount\": 2,\n    \"retweetCount\": 2,\n    \"replyCount\": 2\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/feedback/add?tenantUserName=sampleTenant&topic=My App&source=TWITTER",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"add"
							],
							"query": [
								{
									"key": "tenantUserName",
									"value": "sampleTenant"
								},
								{
									"key": "topic",
									"value": "My App"
								},
								{
									"key": "source",
									"value": "TWITTER"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "addFeedbackPlayStore",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n    \"id\": 2,\n    \"username\": \"abc\",\n    \"created_at\": \"2015-07-05T22:02:57.980Z\",\n    \"review\": \"Good App\",\n    \"rating\": 5,\n    \"appVersion\": \"2.1\",\n    \"likeCount\": 4\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/feedback/add?tenantUserName=sampleTenant&topic=My App&source=PLAY_STORE",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"add"
							],
							"query": [
								{
									"key": "tenantUserName",
									"value": "sampleTenant"
								},
								{
									"key": "topic",
									"value": "My App"
								},
								{
									"key": "source",
									"value": "PLAY_STORE"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllFeedback",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/feedback/getAll",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"getAll"
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllFeedbackForATopicAndTenant",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/feedback/getAllForTopic?tenantUserName=sampleTenant&topic=My App",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"getAllForTopic"
							],
							"query": [
								{
									"key": "tenantUserName",
									"value": "sampleTenant"
								},
								{
									"key": "topic",
									"value": "My App"
								}
							]
						}
					},
					"response": []
				},
				{
					"name": "getAllFeedbackForATopicAndTenantFromASource",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8080/feedback/getAll",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"feedback",
								"getAll"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}
```

