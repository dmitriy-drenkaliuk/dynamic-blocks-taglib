class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'dynamicElements', view: 'index')
		"500"(view:'/error')
	}
}
