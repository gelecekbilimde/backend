package org.gelecekbilimde.scienceplatform;

import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public abstract class UnitTest {

	protected Token mockAdminUserToken = Token.builder()
		.accessToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiI4MzY1MmU5Mi02OWY4LTQzZGEtYmEwNi1jYzQ3OGMwY2ZlNTciLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTQ4NTYzMzQsImV4cCI6MTY5NDg2MzUzNCwidXNlckxhc3ROYW1lIjoiU2lzdGVtaSIsInVzZXJUeXBlIjoiU1VQRVJfQURNSU4iLCJ1c2VyRmlyc3ROYW1lIjoiQWZldCBZw7ZuZXRpbSIsInVzZXJJZCI6ImVhMjlhMGJkLWFkNWQtNDA2OC04OGMzLWE4ODM1YjhkNTU1ZSIsInVzZXJuYW1lIjoiYXlzLXN1cGVyLWFkbWluIn0.h2S5pUhvVVkf8kRf9fVBLZHV5Etf4wODC68RIaFExikD_2cEIQUSc4ZVUsmWQtSJPBgHJBvhNEiBx12GNOF8RNQVlISD-18d2C8sv6YRo_KOYQU67s0sJwLH0LDBe2Tdk_2iqvDMbEDuUFGe7UViC0vaL5fCe98sGrDbU11dYEpco8wA5k7gICxb42X-7lmKFbh7MFG4TmaF3OX0oWeBpODOKHYEcYHnbZZUGfei7WKMdu07A2S-Vm5gu9KnUjnmZTJf1Tr5pEC-eNxR_pyKZXcPhizgeZvx5hwDOb0QzT3rJANO1d1UPmO0QegnwU1VALXLnKE5pDKR0zhtFGbN0A")
		.refreshToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJlYjI2YmVjOS02YWE3LTRmN2YtOWFhYy1mYTJjYTBlMjA4N2IiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTQ4NTYzMzQsImV4cCI6MTY5NDk0MjczNCwidXNlcklkIjoiZWEyOWEwYmQtYWQ1ZC00MDY4LTg4YzMtYTg4MzViOGQ1NTVlIn0.L8K3zPMsG2WnCfpkMCd5a4JbwPEm1Lacg9xvV6CfCI0I9UYX9kXnjGlLk2z2OwHPlkSNccqO_mVih-S6aCQ1arc_0wL-CXBlXDjoo7P-bg2AM0nWkaiNfm7hzyHX3lj6EqbLdXXZaWyAQYIduu0Kdva-BFoOSj5LjDB07OsAX5YvfaftLiSQJp4rJKw-B77u7Zh52sNzDX0x9D_r0i-wlXco3raIxUTg9v9E3SfQrIZNeZ2H9TE-vqzcb1ER1RnAGPTDLv45DjbGOEiDqA13n7qk5yqHFWFwPqkCx8wjqO7zT8rM6LdY3H4v7Kl6KtO1Q8WjpY_DxB6KmAr5PveDyg")
		.build();

	protected Token mockUserToken = Token.builder()
		.accessToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJhZTljZjU4Zi02NWZhLTQ2YWQtYTUwMC1iMjM4YWI2MDkwZTMiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTQ4NTY1NDAsImV4cCI6MTY5NDg2Mzc0MCwiaW5zdGl0dXRpb25JZCI6Ijc3ZWNlMjU2LWJmMGUtNGJiZS04MDFkLTE3MzA4M2Y4YmRjZiIsInVzZXJMYXN0TmFtZSI6IlNpc3RlbWkiLCJpbnN0aXR1dGlvbk5hbWUiOiJUZXN0IEluc3RpdHV0aW9uIDEiLCJ1c2VyVHlwZSI6IkFETUlOIiwidXNlckZpcnN0TmFtZSI6IkFmZXQgWcO2bmV0aW0iLCJ1c2VySWQiOiI5MjYyZjBmYy05M2RiLTRmN2UtODFjNi1hYWFkODVjMmIyMDYiLCJ1c2VybmFtZSI6ImF5cy1hZG1pbi0xIn0.DNTtXLS3Xsn5BDMJimlB9CmM_c-4dAWcCleMeAm0SDldC0QiQRwpMEj-gZxBpgD675QTMBGstMjBxn_Ae7ljmec98lG0qboZUBAIFtGdX7i7_xhDSceTvYOtUQUkw-8P_eQn90U5zVRcvIe-fp1rsHKPYQRPsJ70EPnalV20LSpqz2jHabW7wOxuulaBa94gyJLypE0RCptWFPzBEY5BJTav4V5EgzOf_0dXXfe1b1W2uvNcTUsgNrCwIYyW0D4MSvwjfQk8PSflm_77vFmmT5T4nIO5z2J8PTVNnR3SopkisKAgi71R4TyFNlv6SidGh3JY2DYxul8I03i2QnjnyQ")
		.refreshToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiIzN2IxNmM4Mi0zYzIxLTQ5Y2YtYTRlMC00N2IwOGRmMDZjMzAiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTQ4NTY1NDAsImV4cCI6MTY5NDk0Mjk0MCwidXNlcklkIjoiOTI2MmYwZmMtOTNkYi00ZjdlLTgxYzYtYWFhZDg1YzJiMjA2In0.Vehx3UXB0YEjm_l2DP1EjA5iXe5tJ8CTZefSSz3lv1o3KG3kI7esE3YVTJNbpexKcsEJy0FwHynitq6u1m7foPmfoHtuEOEQAInvERVLeyHcYp_q3z84wqkIYv1KzKaRnNZpPRvvTagKQqQWWALDwU74D9w0am47jZv3BJ1rSoP82ULfrF6UncUFQCymOTCsBbZFWy2BEvGVt6vIUYOX_SRIzh38sY3VRRdNZEO_UzDA0za6ivRfrfmbiIr-tCsSadrs_vgMHtfxf-eaQPDiRCZfuo9lsd9UodnN3Fccpv-72SE-AaqhXowGe-nKXQYLBSq7ZvDIQoP-yVslmt-tAw")
		.build();

	protected Token mockModeratorToken = Token.builder()
		.accessToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJjYTIzYzQ4Yi0zMmMxLTQwYzEtOGNkZS02NjQxZTgyYzU3ZmEiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTk4MTY3NTIsImV4cCI6MTY5OTgyMzk1MiwiaW5zdGl0dXRpb25JZCI6Ijc3ZWNlMjU2LWJmMGUtNGJiZS04MDFkLTE3MzA4M2Y4YmRjZiIsInJvbGVzIjpbIlZPTFVOVEVFUiJdLCJ1c2VyVHlwZSI6IlVTRVIiLCJ1c2VySWQiOiJjNGI0ZTRkYi01NjQxLTQxZjctODIyMi1hNzZkZWIxYzA2NWMiLCJ1c2VybmFtZSI6IjIzMjE4MCJ9.NVR0f2ErLToFi7GOVVhlLVlHzRZaLjJAoZtWRoiZ6tsaRV844un1jFRf303a4UzjtLAGuXXU16dHsgvyE_JbLofu2cQ2GENL2idvpIwbUxani3jisfBozEbt7k0VXOtuIw8pS933bEMHPlnboO3b4el48La1KWbgb9OdyVDqI9-YpWOseyJH6iMAx0gdq6yYEnPHlj7wiG6U4PSjH8WzHDjXSQ6RX9zC8BPUE8PGZM5S_qNidmD293ZR01RGTC37Ah51wxyAlCQtpqMiOnC10yAzRm96y38ljXe8XwgluMFV1YO-8CP1Oj1M80L3q1Zkyj91f8Qb8EkBwm-KydJRbw")
		.refreshToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJiNTFhODMwMC0xMjg4LTQxYzQtOGI4Zi03MGI3OTc3OTBlMDAiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTk4MTY3NTIsImV4cCI6MTY5OTkwMzE1MiwidXNlcklkIjoiYzRiNGU0ZGItNTY0MS00MWY3LTgyMjItYTc2ZGViMWMwNjVjIn0.aPPB30ihZqnZllGwjpzXbK_oH-BkY9T1xcuBqaKkmYMyoYPcMgiIwrIpdhduO3qmcSF7SuNydZPDy6jdVkfzt_A_Y1xwihcLO_S3_gmtav5ydDBEmS5y1HizbnIWibEjiLe0j3gQF3cBySs5WPUWIaKFDx-3tqrd_wUan3-FbSSevO9zzd38NULAJNqwlHq_X1xz8j65vkJvN7jxQ9r1-ks_vzFg5MCrl60I4HzclznMfEiOOsCD_BCRWyBf985U5eELScOyRvx_SAaQ7xY4C5nJu1hFRj4AhPiLWOLWxbxmE2rrbMM8KdkiDhWiO9Y3sdDv3QRFEvRGRlk-HyWFrQ")
		.build();

	protected Token mockAuthorToken = Token.builder()
		.accessToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJjYTIzYzQ4Yi0zMmMxLTQwYzEtOGNkZS02NjQxZTgyYzU3ZmEiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTk4MTY3NTIsImV4cCI6MTY5OTgyMzk1MiwiaW5zdGl0dXRpb25JZCI6Ijc3ZWNlMjU2LWJmMGUtNGJiZS04MDFkLTE3MzA4M2Y4YmRjZiIsInJvbGVzIjpbIlZPTFVOVEVFUiJdLCJ1c2VyVHlwZSI6IlVTRVIiLCJ1c2VySWQiOiJjNGI0ZTRkYi01NjQxLTQxZjctODIyMi1hNzZkZWIxYzA2NWMiLCJ1c2VybmFtZSI6IjIzMjE4MCJ9.NVR0f2ErLToFi7GOVVhlLVlHzRZaLjJAoZtWRoiZ6tsaRV844un1jFRf303a4UzjtLAGuXXU16dHsgvyE_JbLofu2cQ2GENL2idvpIwbUxani3jisfBozEbt7k0VXOtuIw8pS933bEMHPlnboO3b4el48La1KWbgb9OdyVDqI9-YpWOseyJH6iMAx0gdq6yYEnPHlj7wiG6U4PSjH8WzHDjXSQ6RX9zC8BPUE8PGZM5S_qNidmD293ZR01RGTC37Ah51wxyAlCQtpqMiOnC10yAzRm96y38ljXe8XwgluMFV1YO-8CP1Oj1M80L3q1Zkyj91f8Qb8EkBwm-KydJRbw")
		.refreshToken("eyJ0eXAiOiJCZWFyZXIiLCJhbGciOiJSUzUxMiJ9.eyJqdGkiOiJiNTFhODMwMC0xMjg4LTQxYzQtOGI4Zi03MGI3OTc3OTBlMDAiLCJpc3MiOiJBWVMiLCJpYXQiOjE2OTk4MTY3NTIsImV4cCI6MTY5OTkwMzE1MiwidXNlcklkIjoiYzRiNGU0ZGItNTY0MS00MWY3LTgyMjItYTc2ZGViMWMwNjVjIn0.aPPB30ihZqnZllGwjpzXbK_oH-BkY9T1xcuBqaKkmYMyoYPcMgiIwrIpdhduO3qmcSF7SuNydZPDy6jdVkfzt_A_Y1xwihcLO_S3_gmtav5ydDBEmS5y1HizbnIWibEjiLe0j3gQF3cBySs5WPUWIaKFDx-3tqrd_wUan3-FbSSevO9zzd38NULAJNqwlHq_X1xz8j65vkJvN7jxQ9r1-ks_vzFg5MCrl60I4HzclznMfEiOOsCD_BCRWyBf985U5eELScOyRvx_SAaQ7xY4C5nJu1hFRj4AhPiLWOLWxbxmE2rrbMM8KdkiDhWiO9Y3sdDv3QRFEvRGRlk-HyWFrQ")
		.build();

}
