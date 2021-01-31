db.createCollection("TRANSPORTATION COMPANY",
					{"validator":{$jsonSchema:
					{"bsonType":"object",  
					"properties":{"_id":{"bsonType":"string"},
					"TRANSPORTATION COMPANY":{"bsonType":"object",
											  "properties":{"name":{"bsonType": "String" },
															"budget":{"bsonType": "Double" },
															"Owns":{"bsonType":"array",
																	"items": {"bsonType":"object",
																			  "properties":{"registration":{"bsonType": "String" },
																							"manufacturer":{"bsonType": "String" },
																							"speed":{"bsonType": "Double" },
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
                    });
db.TRANSPORTATION_COMPANY