//question 1 
db.createCollection("task1",
					{"validator":{$jsonSchema:
					{"bsonType":"object",  
					"properties":{"_id":{"bsonType":"string"},
					              "TRANSPORTATION COMPANY":{"bsonType":"object",
											  "properties":{"name":{"bsonType": "String" },
															"budget":{"bsonType": "number" },
															"Owns":{"bsonType":"array",
																	"items": {"bsonType":"object",
																			  "properties":{"registration":{"bsonType": "String" },
																							"manufacturer":{"bsonType": "String" },
																							"speed":{"bsonType": "number" },
                                                                                            },
                                                                                "required":["registration", "manufacturer", "speed"],
                                                                                "addittionalProperties": false
                                                                              }
                                                                    },
															"Employs":{"bsonType":"array",
																	   "items": {"bsonType":"object",
																			  "properties":{"license":{"bsonType": "String" },
																							"full name":{"bsonType": "String" },
																							"date of birth":{"bsonType": "Date" },
                                                                                            },
                                                                                "required":["license", "full name", "date of birth"],
                                                                                "addittionalProperties": false
                                                                                }
                                                                    }
                                                            },
                                                "required":["name", "budget", "Owns"],
                                                "addittionalProperties": false
                                                }
                                }
                    }
                    }
                });

//question2
db.task1.insert({"_id": ObjectId(),
                "TRANSPORTATION COMPANY":{"name": "Average Joes Trucks",
                                          "budget": 1000000,
                                          "Owns": [{"registration": "MAD123", 
                                                    "manufacturer": "TOYOTA", 
                                                    "speed": 120},
                                                   {"registration": "YEW000", 
                                                    "manufacturer": "HYUNDAI", 
                                                    "speed": 110}],
                                          "Employs": [{"license":"45635",
                                                       "full name": "John Smith",
                                                       "date of birth":  new Date("1994-03-27"), 
                                                        },
                                                      {"license":"34534",
                                                       "full name": "Jake Greentree",
                                                       "date of birth": new Date("1990-08-12"), 
                                                        }]}
            });

//question 3
db.task1.insert({"_id": ObjectId(),
                "TRANSPORTATION COMPANY":{"name": "Jon Henriks deliveries",
                                          "Owns": [{"registration": "JOGON9", 
                                                    "manufacturer": "PORSCHE", 
                                                    "speed": 1000},
                                                   {"registration": "MATE21", 
                                                    "manufacturer": "FERRARI", 
                                                    "speed": 1100}],
                                          "Employs": [{"license":"57978",
                                                       "full name": "Jack Banana",
                                                       "date of birth":  new Date("1800-03-27"), 
                                                        },
                                                      {"license":"23768",
                                                       "full name": "Paul Kelly",
                                                       "date of birth": new Date("1900-08-12"), 
                                                        }]}
            });

//question 4
print("The document was missing a 'required' property: 'budget'.");